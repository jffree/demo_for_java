package com.jffree.java_demo.concurrent.thread_test;

import java.util.stream.IntStream;

public class ThreadLocalTest implements Runnable{
    public static ThreadLocal<String> data = new ThreadLocal<String>(){
        @Override
        protected String initialValue(){
            return "Initial value: " + Thread.currentThread().getName();
        }
    };

    private static ThreadLocal<String> data2 = new ThreadLocal<String>(){
        @Override
        protected String initialValue(){
            return "Second value: " + Thread.currentThread().getName();
        }
    };

    @Override
    public void run(){
        System.out.println(data.get());
        System.out.println(data2.get());
        data.set(Thread.currentThread().getName() + " add data");
        data2.set(Thread.currentThread().getName() + " add 2 data");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(data.get());
        System.out.println(data2.get());
        data.remove();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data2.remove();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,2).forEach( i -> new Thread(new ThreadLocalTest(), String.format("Thread-%d", i)).start());
    }
}
