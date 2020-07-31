package com.jffree.java_demo.reflect;

public class ClassReflectTest {

    public static void main(String[] args) {

        try {
            // 方法1：Class.forName("类名字符串")  （注意：类名字符串必须是全称，包名+类名）
            Class<?> cls1 = Class.forName("com.jffree.java_demo.reflect.ClassReflectTest$Person");

            // 方法2：类名.class
            Class cls2 = Person.class;

            // 方法3：实例对象.getClass()
            Person person = new Person();
            Class cls3 = person.getClass();

            // 方法4："类名字符串".getClass()
            String str = "com.skywang.test.Person";
            Class cls4 = str.getClass();

            System.out.printf("cls1=%s\n, cls2=%s\n, cls3=%s\n, cls4=%s\n", cls1, cls2, cls3, cls4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Person {
        public Person() {
            System.out.println("create Person");
        }
    }
}
