package com.jffree.java_demo.concurrent.lock_test;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        //normalUse();
        blockUse();
    }

    private static void normalUse(){
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("周末了我在打游戏");
                LockSupport.park();
                System.out.println("陪女朋友逛逛街");
            }
        });
        threadA.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("女朋友准备要喊男朋友逛街");
        LockSupport.unpark(threadA);
    }

    private static void blockUse(){
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("周末了我在打游戏");
                LockSupport.park();
                System.out.println("准备陪女朋友逛逛街");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.park();
                System.out.println("陪女朋友逛逛街");
            }
        });
        threadA.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("女朋友准备要喊男朋友逛街");
        LockSupport.unpark(threadA);
        LockSupport.unpark(threadA);
    }
}
