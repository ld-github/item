package com.ld.web.task;

import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.ld.web.been.model.CodeRepository;
import com.ld.web.biz.CodeRepositoryBiz;
import com.ld.web.component.ApplicationContextHolder;
import com.ld.web.enumeration.CloneStatus;
import com.ld.web.util.JGitTool;

/**
 * 
 *<p>Title: CloneRepositoryTask</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-20
 */
public class CloneRepositoryTask {

    private static Logger logger = Logger.getLogger(CloneRepositoryTask.class);

    private static final CloneRepositoryTask INSTANCE = new CloneRepositoryTask();

    private LinkedBlockingQueue<CodeRepository> taskQueue = new LinkedBlockingQueue<CodeRepository>();

    private Executor taskListenerExecutor = Executors.newSingleThreadExecutor();

    private Executor workExecutor = Executors.newFixedThreadPool(5);

    public void put(CodeRepository c) throws InterruptedException {
        if (!isExist(c)) {
            taskQueue.put(c);
        }
    }

    public CodeRepository take() throws InterruptedException {
        return taskQueue.take();
    }

    public int getTaskSize() {
        return taskQueue.size();
    }

    public boolean isExist(CodeRepository c) {
        Iterator<CodeRepository> it = taskQueue.iterator();

        while (it.hasNext()) {
            CodeRepository codeRepository = (CodeRepository) it.next();
            if (codeRepository.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public int getActiveCount() {
        return ((ThreadPoolExecutor) workExecutor).getActiveCount();
    }

    public static CloneRepositoryTask getInstance() {
        return INSTANCE;
    }

    public void excute() {

        taskListenerExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int activeCount = ((ThreadPoolExecutor) workExecutor).getActiveCount();
                        int corePoolSize = ((ThreadPoolExecutor) workExecutor).getCorePoolSize();

                        if (activeCount == corePoolSize) {
                            continue;
                        }

                        CodeRepository repo = take();

                        workExecutor.execute(new Worker(repo));
                    } catch (InterruptedException e) {
                        logger.info(String.format("Take code repository error: %s", e.getMessage()), e);
                    }
                }
            }
        });
    }

    private CloneRepositoryTask() {
        excute();
    }

    public class Worker implements Runnable {

        private CodeRepository code;

        CodeRepositoryBiz biz = (CodeRepositoryBiz) ApplicationContextHolder.getSpringBean("codeRepositoryBizImpl");

        @Override
        public void run() {
            CodeRepository c = biz.get(code.getId());

            if (null == c) {
                return;
            }

            c.setCloneStatus(CloneStatus.CLONE_ING);
            biz.update(c);

            try {
                JGitTool gitTool = new JGitTool(c.getLocalPath(), c.getRemotePath(), c.getUsername(), c.getPassword());
                gitTool.cloneRepo();
                gitTool.closeRepo();

                c.setCloneStatus(CloneStatus.CLONE_SUCC);
            } catch (Exception e) {
                logger.error(String.format("Clone error: %s", e.getMessage()), e);

                c.setCloneStatus(CloneStatus.CLONE_ERR);
            }

            biz.update(c);
        }

        public Worker(CodeRepository code) {
            this.code = code;
        }
    }

}
