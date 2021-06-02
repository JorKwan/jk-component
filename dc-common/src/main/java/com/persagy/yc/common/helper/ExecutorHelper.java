package com.persagy.yc.common.helper;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池助手
 * @author Charlie Yu
 * @version 1.0 2021-03-02
 */
public final class ExecutorHelper {

    /**
     * 线程池
     */
    private static ExecutorService executor = null;

    /**
     * 通过线程池执行任务,无需同步
     * @param run 可运行的对象
     */
    public static void execute(Runnable run) {
        getExecutor().execute(run);
    }

    /**
     * 通过线程池执行任务,并返回同步点
     * @param task 线程对象
     * @return 同步点对象,同步点只能返回null,
     */
    public static Future<?> submitRunable(Runnable task) {
        return getExecutor().submit(task);
    }

    /**
     * 通过线程池执行任务,并返回同步点
     * @param task 可回调对象
     * @return 同步点对象,可以取得异步返回结果,
     */
    public static <T> Future<T> submit(Callable<T> task) {
        return getExecutor().submit(task);
    }

    /**
     * 通过线程池执行任务列表,并返回同步点列表
     * @param tasks 任务列表
     * @return 同步点对象,可以取得异步返回结果,
     * @throws InterruptedException 中断异常
     */
    public static <T> List<Future<T>> submitAll(Collection<Callable<T>> tasks) throws InterruptedException {
        return getExecutor().invokeAll(tasks);
    }

    /**
     * 通过线程池执行任务列表,并返回第一个成功的结果
     * @param tasks 任务列表
     * @param <T> 结果泛型
     * @return 一个任务的结果对象
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static <T> T submitAny(Collection<Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return getExecutor().invokeAny(tasks);
    }

    /**
     * 取得线程池
     * @return 线程池服务
     */
    public static ExecutorService getExecutor() {
        if (executor == null) {
            init();
        }
        return executor;
    }

    /**
     * 初始化线程池服务
     */
    public static synchronized void init() {
        if (executor != null) {
            return;
        }
        ThreadPoolTaskExecutor poolTaskExecutor = SpringHelper.getBean(
                SpringHelper.getString("threadpool.name", "threadPoolTaskExecutor"), ThreadPoolTaskExecutor.class);
        if (poolTaskExecutor != null) {
            executor = poolTaskExecutor.getThreadPoolExecutor();
        } else {
            //改成无限长的线程池,看看有没有问题
            executor = Executors.newCachedThreadPool();
            //executor = Executors.newWorkStealingPool();

            /*ThreadPoolExecutor executorTest = new ThreadPoolExecutor(10,
                    AppConfig.getInstance().getInt("restcaller.poolnum", 20), 10, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
            executorTest.allowCoreThreadTimeOut(true);
            executor = executorTest;*/
        }
    }
}
