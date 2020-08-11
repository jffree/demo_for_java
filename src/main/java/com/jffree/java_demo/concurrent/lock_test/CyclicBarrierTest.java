package com.jffree.java_demo.concurrent.lock_test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static int SIZE = 5;
    private static CyclicBarrier cb;


    public static void main(String[] args) {
        cb = new CyclicBarrier(SIZE);
        // 新建5个任务
        for(int i=0; i<SIZE; i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");

                        // 将cb的参与者数量加1
                        cb.await();

                        // cb的参与者数量等于5时，才继续往后执行
                        System.out.println(Thread.currentThread().getName() + " continued.");
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }
}
