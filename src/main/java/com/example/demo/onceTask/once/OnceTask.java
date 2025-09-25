package com.example.demo.onceTask.once;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fands
 * @Title: OnceTask
 * @Package com.zmdms.common.core
 * @Description: 单次任务，在分布式环境下，只执行一次
 * @date 2024/12/16 9:27
 * @Version 1.0.0
 */
@Slf4j
public class OnceTask {
    private RedissonClient redissonClient;

    public OnceTask(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public <R> RQueue<R> queueData(OnceTaskLock onceTaskLock, String lockId, TaskAcquirer<R> taskAcquirer) {
        String lockKey = onceTaskLock.getLockKey() + ":" + lockId;
        return queueData0(lockKey, onceTaskLock.getQueueKey(), onceTaskLock.getWaitTime(), taskAcquirer);
    }

    public <R> RQueue<R> queueData(OnceTaskLock onceTaskLock, TaskAcquirer<R> taskAcquirer) {
        return queueData0(onceTaskLock.getLockKey(), onceTaskLock.getQueueKey(), onceTaskLock.getWaitTime(), taskAcquirer);
    }

    private <R> RQueue<R> queueData0(String lockKey, String queueKey, Long waitTime, TaskAcquirer<R> taskAcquirer) {
        Assert.hasText(lockKey, "lockKey不能为空");
        Assert.hasText(queueKey, "queueKey不能为空");
        waitTime = null == waitTime ? 0L : waitTime;
        RLock lock = redissonClient.getLock(lockKey);
        RQueue<R> queue = redissonClient.getQueue(queueKey);
        try {
            if (lock.tryLock(waitTime, TimeUnit.SECONDS) && queue.isEmpty()) {
                List<R> list = taskAcquirer.acquire();
                if (CollectionUtil.isNotEmpty(list)) {
                    queue.addAll(list);
                }
            }
        }catch (InterruptedException e) {
            log.error("OnceTask doTask error, lockKey: {}", lockKey, e);
        }finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return queue;
    }

    public void exec(OnceTaskLock onceTaskLock, String lockId, TaskExecuter executer) {
        String lockKey = onceTaskLock.getLockKey() + lockId;
        exec0(lockKey, onceTaskLock.getWaitTime(), executer);

    }

    public void exec(OnceTaskLock onceTaskLock, TaskExecuter executer) {
        exec0(onceTaskLock.getLockKey(), onceTaskLock.getWaitTime(), executer);
    }

    private void exec0(String lockKey, Long waitTime, TaskExecuter executer) {
        Assert.hasText(lockKey, "lockKey不能为空");
        waitTime = null == waitTime ? 0L : waitTime;

        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(waitTime, TimeUnit.SECONDS)) {
                executer.execute();
            }
        }catch (InterruptedException e) {
            log.error("OnceTask exec error, lockKey: {}", lockKey, e);
        }finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
