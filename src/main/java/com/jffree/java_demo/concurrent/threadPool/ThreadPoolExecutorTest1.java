package com.jffree.java_demo.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest1 {
    private static AtomicInteger id = new AtomicInteger();
    private static ExecutorService exec = new ThreadPoolExecutor(5, 8, 0,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), r -> {
     Thread t = new Thread(r);
     t.setName("worker-tread-" + id.getAndIncrement());
     System.err.println("add worker thread: " + t.getName());
     return t;
    }, (r, executor) -> {
                if(!executor.isShutdown()){
                    try {
                        System.err.println("waiting queue is full, putting...");
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } }
                );

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger i = new AtomicInteger();
        while (true){
            exec.execute(()->{
                String task = "Task: " + i.getAndIncrement();
               System.out.println(Thread.currentThread().getName() + " execute " + task);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(task + " end.");
            });
            Thread.sleep(100);
        }
    }

}
