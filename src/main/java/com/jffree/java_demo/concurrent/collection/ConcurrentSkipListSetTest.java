package com.jffree.java_demo.concurrent.collection;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentSkipListSetTest {

    // TODO: set是TreeSet对象时，程序会出错。
    //private static Set<String> set = new TreeSet<String>();
    private static Set<String> set = new ConcurrentSkipListSet<String>();

    public static void main(String[] args) {

        // 同时启动两个线程对set进行操作！
        new MyThread("a").start();
        new MyThread("b").start();
    }

    private static void printAll() {
        String value = null;
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            value = (String) iter.next();
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 10) {
                // “线程名” + "序号"
                String val = Thread.currentThread().getName() + (i % 6);
                set.add(val);
                // 通过“Iterator”遍历set。
                printAll();
            }
        }
    }

}
