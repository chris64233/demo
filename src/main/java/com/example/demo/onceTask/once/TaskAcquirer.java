package com.example.demo.onceTask.once;

import java.util.List;

/**
 * @author fands
 * @Title: TaskAcquirer
 * @Package com.zmdms.common.task
 * @Description: 获取任务要处理的数据
 * @date 2024/12/16 12:49
 * @Version 1.0.0
 */
@FunctionalInterface
public interface TaskAcquirer<R> {
    List<R> acquire();
}
