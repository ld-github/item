package com.ld.web.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ld.web.been.model.Task;
import com.ld.web.thread.PublishJob;

/**
 * 
 *<p>Title: PublishTaskManager</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-06
 */
public class PublishTaskManager {

    private static final PublishTaskManager INSTANCE = new PublishTaskManager();

    ExecutorService pool = Executors.newFixedThreadPool(5);

    public void put(Task t) {
        pool.execute(new PublishJob(t));
    }

    public static PublishTaskManager getInstance() {
        return INSTANCE;
    }

    private PublishTaskManager() {
    }
}
