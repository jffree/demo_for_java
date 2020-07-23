package com.jffree.java_demo.base;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReferenceTest {


    public static class MyDate extends Date {

        /** Creates a new instance of MyDate */
        public MyDate() {
        }
        // 覆盖finalize()方法
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("obj [Date: " + this.getTime() + "] is gc");
        }

        public String toString() {
            return "Date: " + this.getTime();
        }
    }


    //在运行下面的Java代码之前，需要先配置参数 -Xms2M -Xmx3M，将 JVM 的初始内存设为2M，最大可用内存为 3M。
    //每个测试需要单独进行，方能看出效果
    public static void main(String[] args) {
        testStrongReference();
        testSoftReference();
        testWeakReference();
        testPhantomReference();
    }

    private static void testStrongReference() {
        MyDate[] buff = new MyDate[1024 * 1024 * 3];
    }

    private static void testSoftReference() {
        List<SoftReference<MyDate[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyDate[] buff = new MyDate[1024 * 1024];
            SoftReference<MyDate[]> sr = new SoftReference<>(buff);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }

    }

    private static void testWeakReference() {
        List<WeakReference<MyDate[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyDate[] buff = new MyDate[1024 * 1024];
            WeakReference<MyDate[]> sr = new WeakReference<>(buff);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((WeakReference) list.get(i)).get();
            System.out.println(obj);
        }
    }

    private static void testPhantomReference() {
        ReferenceQueue queue = new ReferenceQueue();
        List<PhantomReference<MyDate[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyDate[] buff = new MyDate[1024 * 1024];
            PhantomReference<MyDate[]> sr = new PhantomReference<>(buff, queue);
            list.add(sr);
        }

        System.gc(); //主动通知垃圾回收

        for(int i=0; i < list.size(); i++){
            Object obj = ((PhantomReference) list.get(i)).get();    //虚引用返回的永远都是空
            System.out.println(obj);
        }
    }
}
