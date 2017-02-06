package com.ld.web.thread;

import com.ld.web.been.model.Task;
import com.ld.web.task.PublishTaskManager;

public class PublishJob implements Runnable {

    @Override
    public void run() {
        System.out.println("开始执行线程");
        while (true) {
            try {
                System.out.println("尝试拿取任务");
                Task task = PublishTaskManager.getInstance().get();
                System.out.println("拿到任务");
                System.out.println(task.getId());
            } catch (InterruptedException e) {
            }

        }
    }
}
