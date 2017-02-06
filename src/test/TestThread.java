package test;

import com.ld.web.been.model.Task;
import com.ld.web.task.PublishTaskManager;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    Task t = new Task();
                    t.setId(i + "");

                    try {
                        PublishTaskManager.getInstance().put(t);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1000; i < 2000; i++) {
                    Task t = new Task();
                    t.setId(i + "");

                    try {
                        PublishTaskManager.getInstance().put(t);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
