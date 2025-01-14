package com.example.demo.task.once;

/**
 * @author fands
 * @Title: OnceTaskLock
 * @Package com.zmdms.common.task
 * @Description:
 * @date 2024/12/16 9:36
 * @Version 1.0.0
 */
public interface OnceTaskLock {

    String getLockKey();

    String getQueueKey();

    Long getWaitTime();
}
