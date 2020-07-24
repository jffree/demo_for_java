package com.jffree.java_demo.reflect;

import java.lang.annotation.ElementType;

public class ClassReflectOtherApiTest {

    public static void main(String[] args) {

        Class cls = ClassReflectOtherApiTest.class;
        // 获取“类名”
        System.out.printf("%-50s:getSimpleName()=%s\n", cls, cls.getSimpleName());
        // 获取“完整类名”
        System.out.printf("%-50s:getName()=%s\n", cls, cls.getName());

        // 测试其它的API
        testOtherAPIs() ;
    }

    public static void testOtherAPIs() {
        // 本地类
        class LocalA {
        }

        // 测试枚举类型。ElementType是一个枚举类
        Class elementtypeCls = ElementType.class;
        System.out.printf("%-50s:isEnum()=%s\n",
                elementtypeCls, elementtypeCls.isEnum());

        // 判断是不是类的对象
        Class demoCls = ClassReflectOtherApiTest.class;
        ClassReflectOtherApiTest demoObj = new ClassReflectOtherApiTest();
        System.out.printf("%-50s:isInstance(obj)=%s\n",
                demoCls, demoCls.isInstance(demoObj));

        // 类是不是“接口”
        Class runCls = Runnable.class;
        System.out.printf("%-50s:isInterface()=%s\n",
                runCls, runCls.isInterface());

        // 类是不是“本地类”。本地类,就是定义在方法内部的类。
        Class localCls = LocalA.class;
        System.out.printf("%-50s:isLocalClass()=%s\n",
                localCls, localCls.isLocalClass());

        // 类是不是“成员类”。成员类,是内部类的一种，但是它不是“内部类”或“匿名类”。
        Class memCls = MemberB.class;
        System.out.printf("%-50s:isMemberClass()=%s\n",
                memCls, memCls.isMemberClass());

        // 类是不是“基本类型”。 基本类型，包括void和boolean、byte、char、short、int、long、float 和 double这几种类型。
        Class primCls = int.class;
        System.out.printf("%-50s:isPrimitive()=%s\n",
                primCls, primCls.isPrimitive());

        // 类是不是“复合类”。 JVM中才会产生复合类，在java应用程序中不存在“复合类”！
        Class synCls = ClassReflectOtherApiTest.class;
        System.out.printf("%-50s:isSynthetic()=%s\n",
                synCls, synCls.isSynthetic());
    }

    // 内部成员类
    class MemberB {
    }
}
