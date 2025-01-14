package com.example.demo.task;

import com.example.demo.task.once.OnceTask;
import com.example.demo.task.once.Task;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

import static com.example.demo.task.once.PayTaskLock.PROFIT_SHARING;
import static com.example.demo.task.once.PayTaskLock.SHARING_TASK;


@Slf4j
@RestController
@RequestMapping("/oncetask")
public class TestTask {

    private final OnceTask onceTask;

    @Resource
    private Task task;

    public TestTask(OnceTask onceTask) {
        this.onceTask = onceTask;
    }

    @GetMapping("/query")
    public void query() {
        RQueue<String> queue = onceTask.queueData(PROFIT_SHARING, "2024-12-25",
                () -> Arrays.asList("一", "二", "三"));
        if (queue.isEmpty()) {
            return;
        }

        String x = null;
        while ((x = queue.poll()) != null) {
            System.out.println(x);
        }
    }

    @GetMapping("/exec")
    public void exec(@RequestParam("taskId") String taskId) {
        onceTask.exec(SHARING_TASK, taskId, () -> task.doTask(taskId));
    }

}
