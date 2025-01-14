package com.example.demo.task;

import com.example.demo.task.once.OnceTask;
import com.example.demo.task.once.Task;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RQueue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;

import static com.example.demo.task.once.PayTaskLock.PROFIT_SHARING;
import static com.example.demo.task.once.PayTaskLock.SHARING_TASK;


@Slf4j
@Component
public class TestTask {

    private final OnceTask onceTask;

    @Resource
    private Task task;

    public TestTask(OnceTask onceTask) {
        this.onceTask = onceTask;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void sharing() {
        test();
    }

    @GetMapping("/wx/sharingOrder")
    public void sharingOrder(@RequestParam("taskId")String taskId) {
        onceTask.exec(SHARING_TASK, taskId, () -> task.doTask(taskId));

    }

    private void test() {
        RQueue<String> queue = onceTask.queueData(PROFIT_SHARING, "2024-12-25", () -> {
            System.out.println("test once task");
            return new ArrayList<>();
        });

        if (queue.isEmpty()) {
            return;
        }

        String trxNo = null;
        while ((trxNo = queue.poll()) != null) {

        }

    }


}
