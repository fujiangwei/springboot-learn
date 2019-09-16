package com.example.springbootscheduled.timer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/17
 * @time: 16:16
 * @modifier:
 * @since:
 */
public class TimerTest {

    /**
     * 线程池的基本大小
     */
    static int corePoolSize = 10;
    /**
     * 线程池最大数量
     */
    static int maxnumPoolSize = 100;
    /**
     * 线程活动保持时间
     */
    static long keepAliveTime = 1;
    /**
     * 任务队列
     */
    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);

    public static void main(String[] args) {
        scheduledExecutorService();
    }

    public static void timer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task run:" + new Date());
            }
        };

        Timer timer = new Timer();
        //指定的任务在指定的时间后开始进行重复的固定延迟执行。这里是在1s之后每3秒执行一次
        timer.schedule(timerTask, 1, 1000);
    }

    public static void scheduledExecutorService() {
        /**
         * 手动创建线程池
         *      newFixedThreadPool和newSingleThreadPool:
         *          主要问题是堆积请求的处理队列可能会消耗非常大的内存，甚至OOM
         *      newCachedThreadPool和newScheduledThreadPool
         *          主要问题是线程数最大是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM
         */
//        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        //创建线程名
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
//        //手动创建线程池
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxnumPoolSize, keepAliveTime, TimeUnit.SECONDS,
//                workQueue, namedThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());
//
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2, namedThreadFactory);

        //在1秒后每隔2秒执行一次
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduledExecutorService run:" + Thread.currentThread().getName() + "-" + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
}
