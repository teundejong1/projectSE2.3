package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

    private volatile static ThreadPoolExecutor threadPool;

    public ThreadPool() {}

    public static ThreadPoolExecutor getInstance() {
        if (threadPool == null) {
            synchronized (ThreadPool.class) {
                if (threadPool == null) {
                    threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
                }
            }
        }

        return threadPool;
    }

    public static void shutdown() {
        threadPool.shutdown();
    }

}