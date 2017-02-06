package com.ld.web.thread;

import com.ld.web.been.model.Task;

public class PublishJob implements Runnable {

    private Task task;

    @Override
    public void run() {
        try {
            int t = (int) (Math.random() * 10000);
            System.out.println(t + ":" + hashCode() + ":" + Thread.currentThread().getName() + "开始执行线程" + task.getId());

            Thread.sleep(t);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public PublishJob(Task task) {
        this.task = task;
    }

    public PublishJob() {
    }
}
