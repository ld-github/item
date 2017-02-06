package test;

import com.ld.web.been.model.Task;
import com.ld.web.task.PublishTaskManager;

public class TestPoll {
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            Task t = new Task();
            t.setId(i + "");

            PublishTaskManager.getInstance().put(t);
        }
    }
}
