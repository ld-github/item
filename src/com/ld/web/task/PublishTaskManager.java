package com.ld.web.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import com.ld.web.been.model.Task;
import com.ld.web.thread.PublishJob;

public class PublishTaskManager {

    private static final PublishTaskManager INSTANCE = new PublishTaskManager();

    private BlockingQueue<Task> tasks = new LinkedBlockingDeque<Task>();

    public void put(Task t) throws InterruptedException {
        synchronized (INSTANCE) {
            tasks.put(t);
        }
    }

    public Task get() throws InterruptedException {
        synchronized (INSTANCE) {
            return tasks.take();
        }
    }

    ExecutorService pool = Executors.newFixedThreadPool(5);

    public void startTask() {

        for (int i = 0; i < 10; i++) {
            pool.execute(new PublishJob());
        }
    }

    public static PublishTaskManager getInstance() {
        return INSTANCE;
    }

    private PublishTaskManager() {
    }
}
