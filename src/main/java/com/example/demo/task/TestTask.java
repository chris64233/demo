package com.example.demo.task;

import com.example.demo.task.once.OnceTask;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RQueue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.example.demo.task.once.PayTaskLock.PROFIT_SHARING;


@Slf4j
@Component
public class TestTask {

    private final OnceTask onceTask;

    public TestTask(OnceTask onceTask) {
        this.onceTask = onceTask;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void sharing() {
        test();
    }

    private void test() {
        RQueue<String> queue = onceTask.queueData(PROFIT_SHARING, "2024-12-25", () -> {
            System.out.println("test once task");
            return new ArrayList<>();
        });

    }


}
