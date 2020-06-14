package cn.people.utils.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : FENGZHI
 * create at:  2020/4/23  下午8:48
 * @description: 自定义线程池
 */
public class ThreadPoolExecutorUtil {
    public static ThreadPoolExecutor getPool(){
        return new ThreadPoolExecutor(
                1,
                1,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
