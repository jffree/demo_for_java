package com.jffree.java_demo.String;

/**
 * StringBuffer 演示程序
 *
 * @author skywang
 */
import java.util.HashMap;

public class StringBufferTest {

    public static void main(String[] args) {
        testInsertAPIs() ;
        testAppendAPIs() ;
        testReplaceAPIs() ;
        testDeleteAPIs() ;
        testIndexAPIs() ;
        testOtherAPIs() ;
    }

    /**
     * StringBuffer 的其它API示例
     */
    private static void testOtherAPIs() {

        System.out.println("-------------------------------- testOtherAPIs --------------------------------");

        StringBuffer sbuilder = new StringBuffer("0123456789");

        int cap = sbuilder.capacity();
        System.out.printf("cap=%d\n", cap);

        char c = sbuilder.charAt(6);
        System.out.printf("c=%c\n", c);

        char[] carr = new char[4];
        sbuilder.getChars(3, 7, carr, 0);
        for (int i=0; i<carr.length; i++)
            System.out.printf("carr[%d]=%c ", i, carr[i]);
        System.out.println();

        System.out.println();
    }

    /**
     * StringBuffer 中index相关API演示
     */
    private static void testIndexAPIs() {
        System.out.println("-------------------------------- testIndexAPIs --------------------------------");

        StringBuffer sbuilder = new StringBuffer("abcAbcABCabCaBcAbCaBCabc");
        System.out.printf("sbuilder=%s\n", sbuilder);

        // 1. 从前往后，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "sbuilder.indexOf(\"bc\")", sbuilder.indexOf("bc"));

        // 2. 从位置5开始，从前往后，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "sbuilder.indexOf(\"bc\", 5)", sbuilder.indexOf("bc", 5));

        // 3. 从后往前，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "sbuilder.lastIndexOf(\"bc\")", sbuilder.lastIndexOf("bc"));

        // 4. 从位置4开始，从后往前，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "sbuilder.lastIndexOf(\"bc\", 4)", sbuilder.lastIndexOf("bc", 4));

        System.out.println();
    }

    /**
     * StringBuffer 的replace()示例
     */
    private static void testReplaceAPIs() {

        System.out.println("-------------------------------- testReplaceAPIs ------------------------------");

        StringBuffer sbuilder;

        sbuilder = new StringBuffer("0123456789");
        sbuilder.replace(0, 3, "ABCDE");
        System.out.printf("sbuilder=%s\n", sbuilder);

        sbuilder = new StringBuffer("0123456789");
        sbuilder.reverse();
        System.out.printf("sbuilder=%s\n", sbuilder);

        sbuilder = new StringBuffer("0123456789");
        sbuilder.setCharAt(0, 'M');
        System.out.printf("sbuilder=%s\n", sbuilder);

        System.out.println();
    }

    /**
     * StringBuffer 的delete()示例
     */
    private static void testDeleteAPIs() {

        System.out.println("-------------------------------- testDeleteAPIs -------------------------------");

        StringBuffer sbuilder = new StringBuffer("0123456789");

        // 删除位置0的字符，剩余字符是“123456789”。
        sbuilder.deleteCharAt(0);
        // 删除位置3(包括)到位置6(不包括)之间的字符，剩余字符是“123789”。
        sbuilder.delete(3,6);

        // 获取sb中从位置1开始的字符串
        String str1 = sbuilder.substring(1);
        // 获取sb中从位置3(包括)到位置5(不包括)之间的字符串
        String str2 = sbuilder.substring(3, 5);
        // 获取sb中从位置3(包括)到位置5(不包括)之间的字符串，获取的对象是CharSequence对象，此处转型为String
        String str3 = (String)sbuilder.subSequence(3, 5);

        System.out.printf("sbuilder=%s\nstr1=%s\nstr2=%s\nstr3=%s\n",
                sbuilder, str1, str2, str3);

        System.out.println();
    }

    /**
     * StringBuffer 的insert()示例
     */
    private static void testInsertAPIs() {

        System.out.println("-------------------------------- testInsertAPIs -------------------------------");

        StringBuffer sbuilder = new StringBuffer();

        // 在位置0处插入字符数组
        sbuilder.insert(0, new char[]{'a','b','c','d','e'});
        // 在位置0处插入字符数组。0表示字符数组起始位置，3表示长度
        sbuilder.insert(0, new char[]{'A','B','C','D','E'}, 0, 3);
        // 在位置0处插入float
        sbuilder.insert(0, 1.414f);
        // 在位置0处插入double
        sbuilder.insert(0, 3.14159d);
        // 在位置0处插入boolean
        sbuilder.insert(0, true);
        // 在位置0处插入char
        sbuilder.insert(0, '\n');
        // 在位置0处插入int
        sbuilder.insert(0, 100);
        // 在位置0处插入long
        sbuilder.insert(0, 12345L);
        // 在位置0处插入StringBuilder对象
        sbuilder.insert(0, new StringBuffer("StringBuilder"));
        // 在位置0处插入StringBuilder对象。6表示被在位置0处插入对象的起始位置(包括)，13是结束位置(不包括)
        sbuilder.insert(0, new StringBuffer("STRINGBUILDER"), 6, 13);
        // 在位置0处插入StringBuffer对象。
        sbuilder.insert(0, new StringBuffer("StringBuffer"));
        // 在位置0处插入StringBuffer对象。6表示被在位置0处插入对象的起始位置(包括)，12是结束位置(不包括)
        sbuilder.insert(0, new StringBuffer("STRINGBUFFER"), 6, 12);
        // 在位置0处插入String对象。
        sbuilder.insert(0, "String");
        // 在位置0处插入String对象。1表示被在位置0处插入对象的起始位置(包括)，6是结束位置(不包括)
        sbuilder.insert(0, "0123456789", 1, 6);
        sbuilder.insert(0, '\n');

        // 在位置0处插入Object对象。此处以HashMap为例
        HashMap map = new HashMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        sbuilder.insert(0, map);

        System.out.printf("%s\n\n", sbuilder);
    }

    /**
     * StringBuffer 的append()示例
     */
    private static void testAppendAPIs() {

        System.out.println("-------------------------------- testAppendAPIs -------------------------------");

        StringBuffer sbuilder = new StringBuffer();

        // 追加字符数组
        sbuilder.append(new char[]{'a','b','c','d','e'});
        // 追加字符数组。0表示字符数组起始位置，3表示长度
        sbuilder.append(new char[]{'A','B','C','D','E'}, 0, 3);
        // 追加float
        sbuilder.append(1.414f);
        // 追加double
        sbuilder.append(3.14159d);
        // 追加boolean
        sbuilder.append(true);
        // 追加char
        sbuilder.append('\n');
        // 追加int
        sbuilder.append(100);
        // 追加long
        sbuilder.append(12345L);
        // 追加StringBuilder对象
        sbuilder.append(new StringBuffer("StringBuilder"));
        // 追加StringBuilder对象。6表示被追加对象的起始位置(包括)，13是结束位置(不包括)
        sbuilder.append(new StringBuffer("STRINGBUILDER"), 6, 13);
        // 追加StringBuffer对象。
        sbuilder.append(new StringBuffer("StringBuffer"));
        // 追加StringBuffer对象。6表示被追加对象的起始位置(包括)，12是结束位置(不包括)
        sbuilder.append(new StringBuffer("STRINGBUFFER"), 6, 12);
        // 追加String对象。
        sbuilder.append("String");
        // 追加String对象。1表示被追加对象的起始位置(包括)，6是结束位置(不包括)
        sbuilder.append("0123456789", 1, 6);
        sbuilder.append('\n');

        // 追加Object对象。此处以HashMap为例
        HashMap map = new HashMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        sbuilder.append(map);
        sbuilder.append('\n');

        // 追加unicode编码
        sbuilder.appendCodePoint(0x5b57);    // 0x5b57是“字”的unicode编码
        sbuilder.appendCodePoint(0x7b26);    // 0x7b26是“符”的unicode编码
        sbuilder.appendCodePoint(0x7f16);    // 0x7f16是“编”的unicode编码
        sbuilder.appendCodePoint(0x7801);    // 0x7801是“码”的unicode编码

        System.out.printf("%s\n\n", sbuilder);
    }
}
