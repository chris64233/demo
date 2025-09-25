package com.example.demo.onceTask.jupiter;


public class LockUtils {

    public static void execute(String lockKey, String requestId, int expireTime, Runnable task) {
        try {
            if (!JupiterCacheUtil.acquireLock(lockKey, requestId, expireTime)) {
                throw new RuntimeException("正在使用中");
            }

            task.run();
        } finally {
            JupiterCacheUtil.releaseLock(lockKey, requestId);
        }
    }
}
