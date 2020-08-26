package com.jffree.java_demo.concurrent.collection;

import java.util.*;
import java.util.concurrent.*;

public class CopyOnWriteArrayListTest {


    // TODO: list是ArrayList对象时，程序会出错。
    //private static List<String> list = new ArrayList<String>();
    private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {

        // 同时启动两个线程对list进行操作！
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value = Thread.currentThread().getName();
        StringBuilder builder = new StringBuilder(Thread.currentThread().getName()+": ");
        Iterator iter = list.iterator();
        while(iter.hasNext()) {
            builder.append((String)iter.next() + ", ");
        }
        System.out.println(builder.toString());
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                // “线程名” + "-" + "序号"
                String val = Thread.currentThread().getName()+"-"+i;
                list.add(val);
                // 通过“Iterator”遍历List。
                printAll();
            }
        }
    }

}
