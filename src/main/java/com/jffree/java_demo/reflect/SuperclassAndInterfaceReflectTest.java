package com.jffree.java_demo.reflect;

import java.io.Serializable;
import java.lang.reflect.Type;

public class SuperclassAndInterfaceReflectTest {
    public static void main(String[] args) {

        try {
            // 根据“类名”获取 对应的Class对象
            Class<?> cls = Class.forName("com.jffree.java_demo.reflect.SuperclassAndInterfaceReflectTest$Person");

            // 获取“Person”的父类
            Type father = cls.getGenericSuperclass();
            // 获取“Person”实现的全部接口
            Type[] intfs = cls.getGenericInterfaces();

            System.out.println("father="+father);
            for (Type t:intfs)
                System.out.println("t="+t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Person extends Object implements Serializable, Runnable{

        @Override
        public void run() {
        }

    }
}
