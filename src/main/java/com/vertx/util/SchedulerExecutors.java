package com.vertx.util;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by axu on 2015/3/28.
 * 定时器工具
 */
public class SchedulerExecutors {
    static final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

    private static class SingletonHolder {
        static final SchedulerExecutors instance = new SchedulerExecutors();
    }

    public static SchedulerExecutors getInstance() {
        return SingletonHolder.instance;
    }


    public static void schedule(Runnable command, long initialDelay, long period, TimeUnit unit) {
        exec.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    public static void shutdown() {
        if(!exec.isShutdown()) {
            exec.shutdownNow();
        }
    }
}