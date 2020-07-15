package com.jffree.java_demo.String;

/**
 * String 演示程序
 *
 * @author skywang
 */
import java.util.HashMap;
import java.nio.charset.Charset;
import java.io.UnsupportedEncodingException;

public class StringAPITest {

    public static void main(String[] args) {
        testStringConstructors() ; // String 构造函数测试程序
        testValueAPIs() ;          // String 的valueOf()演示程序
        testIndexAPIs() ;          // String 中index相关API演示
        testCompareAPIs() ;        // String 中比较相关API演示
        testModifyAPIs() ;         // String 中 修改(追加/替换/截取/分割)字符串的相关API演示
        testUnicodeAPIs() ;        // String 中与unicode相关的API
        testOtherAPIs() ;          // String 中其它的API
    }

    /**
     * String 构造函数测试程序
     */
    private static void testStringConstructors() {
        try {
            System.out.println("-------------------------------- testStringConstructors -----------------------");

            String str01 = new String();
            String str02 = new String("String02");
            String str03 = new String(new char[]{'s','t','r','0','3'});
            String str04 = new String(new char[]{'s','t','r','0','4'}, 1, 3);          // 1表示起始位置，3表示个数
            String str05 = new String(new byte[]{0x61, 0x62, 0x63, 0x64, 0x65});       // 0x61在ASC表中，对应字符"a"; 1表示起始位置，3表示长度
            String str06 = new String(new byte[]{0x61, 0x62, 0x63, 0x64, 0x65}, 1, 3); // 0x61在ASC表中，对应字符"a"; 1表示起始位置，3表示长度
            String str07 = new String(new byte[]{0x61, 0x62, 0x63, 0x64, 0x65}, 0);       // 0x61在ASC表中，对应字符"a";0，表示“高字节”
            String str08 = new String(new byte[]{0x61, 0x62, 0x63, 0x64, 0x65}, 0, 1, 3); // 0x61在ASC表中，对应字符"a"; 0，表示“高字节”；1表示起始位置，3表示长度
            String str09 = new String(new byte[]{(byte)0xe5, (byte)0xad, (byte)0x97, /* 字-对应的utf-8编码 */
                    (byte)0xe7, (byte)0xac, (byte)0xa6, /* 符-对应的utf-8编码 */
                    (byte)0xe7, (byte)0xbc, (byte)0x96, /* 编-对应的utf-8编码 */
                    (byte)0xe7, (byte)0xa0, (byte)0x81, /* 码-对应的utf-8编码 */ },
                    0, 12, "utf-8");  // 0表示起始位置，12表示长度。
            String str10 = new String(new byte[]{(byte)0x5b, (byte)0x57, /* 字-对应的utf-16编码 */
                    (byte)0x7b, (byte)0x26, /* 符-对应的utf-16编码 */
                    (byte)0x7f, (byte)0x16, /* 编-对应的utf-16编码 */
                    (byte)0x78, (byte)0x01, /* 码-对应的utf-16编码 */ },
                    0, 8, "utf-16");  // 0表示起始位置，8表示长度。
            String str11 = new String(new byte[]{(byte)0xd7, (byte)0xd6, /* 字-对应的gb2312编码  */
                    (byte)0xb7, (byte)0xfb, /* 符-对应的gb2312编码 */
                    (byte)0xb1, (byte)0xe0, /* 编-对应的gb2312编码 */
                    (byte)0xc2, (byte)0xeb, /* 码-对应的gb2312编码 */ },
                    Charset.forName("gb2312"));
            String str12 = new String(new byte[]{(byte)0xd7, (byte)0xd6, /* 字-对应的gbk编码 */
                    (byte)0xb7, (byte)0xfb, /* 符-对应的gbk编码 */
                    (byte)0xb1, (byte)0xe0, /* 编-对应的gbk编码 */
                    (byte)0xc2, (byte)0xeb, /* 码-对应的gbk编码 */ },
                    0, 8, Charset.forName("gbk"));
            String str13 = new String(new int[] {0x5b57, 0x7b26, 0x7f16, 0x7801}, 0, 4);  // "字符编码"(\u5b57是‘字’的unicode编码)。0表示起始位置，4表示长度。
            String str14 = new String(new StringBuffer("StringBuffer"));
            String str15 = new String(new StringBuilder("StringBuilder"));

            System.out.printf(" str01=%s \n str02=%s \n str03=%s \n str04=%s \n str05=%s \n str06=%s \n str07=%s \n str08=%s\n str09=%s\n str10=%s\n str11=%s\n str12=%s\n str13=%s\n str14=%s\n str15=%s\n",
                    str01, str02, str03, str04, str05, str06, str07, str08, str09, str10, str11, str12, str13, str14, str15);


            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * String 中其它的API
     */
    private static void testOtherAPIs() {
        System.out.println("-------------------------------- testOtherAPIs --------------------------------");

        String str = "0123456789";
        System.out.printf("str=%s\n", str);

        // 1. 字符串长度
        System.out.printf("%s = %d\n", "str.length()", str.length());

        // 2. 字符串是否为空
        System.out.printf("%s = %b\n", "str.isEmpty()", str.isEmpty());

        // 3. [字节] 获取字符串对应的字节数组
        byte[] barr = str.getBytes();
        for (int i=0; i<barr.length; i++) {
            System.out.printf("barr[%d]=0x%x ", i, barr[i]);
        }
        System.out.println();

        // 4. [字符] 获取字符串位置4的字符
        System.out.printf("%s = %c\n", "str.charAt(4)", str.charAt(4));

        // 5. [字符] 获取字符串对应的字符数组
        char[] carr = str.toCharArray();
        for (int i=0; i<carr.length; i++) {
            System.out.printf("carr[%d]=%c ", i, carr[i]);
        }
        System.out.println();

        // 6. [字符] 获取字符串中部分元素对应的字符数组
        char[] carr2 = new char[3];
        str.getChars(6, 9, carr2, 0);
        for (int i=0; i<carr2.length; i++) {
            System.out.printf("carr2[%d]=%c ", i, carr2[i]);
        }
        System.out.println();

        // 7. [字符] 获取字符数组对应的字符串
        System.out.printf("%s = %s\n",
                "str.copyValueOf(new char[]{'a','b','c','d','e'})",
                String.copyValueOf(new char[]{'a','b','c','d','e'}));

        // 8. [字符] 获取字符数组中部分元素对应的字符串
        System.out.printf("%s = %s\n",
                "str.copyValueOf(new char[]{'a','b','c','d','e'}, 1, 4)",
                String.copyValueOf(new char[]{'a','b','c','d','e'}, 1, 4));

        // 9. format()示例，将对象数组按指定格式转换为字符串
        System.out.printf("%s = %s\n",
                "str.format()",
                String.format("%s-%d-%b", "abc", 3, true));

        System.out.println();
    }

    /**
     * String 中 修改(追加/替换/截取/分割)字符串的相关API演示
     */
    private static void testModifyAPIs() {
        System.out.println("-------------------------------- testModifyAPIs -------------------------------");

        String str = " abcAbcABCabCAbCabc ";
        System.out.printf("%s, len=%d\n", str, str.length());

        // 1.追加
        // 将"123"追加到str之后
        System.out.printf("%-30s = %s\n", "str.concat(\"123\")",
                str.concat("123"));

        // 2.截取
        // 截取str中从位置7(包括)开始的元素。
        System.out.printf("%-30s = %s\n", "str.substring(7)", str.substring(7));
        // 截取str中从位置7(包括)到位置10(不包括)之间的元素。
        System.out.printf("%-30s = %s\n", "str.substring(7, 10)", str.substring(7, 10));
        // 删除str中首位的空格，并返回。
        System.out.printf("%-30s = %s, len=%d\n", "str.trim()", str.trim(), str.trim().length());

        // 3.替换
        // 将str中的 “字符‘a’” 全部替换为 “字符‘_’”
        System.out.printf("%-30s = %s\n", "str.replace('a', 'M')", str.replace('a', '_'));
        // 将str中的第一次出现的“字符串“a”” 替换为 “字符串“###””
        System.out.printf("%-30s = %s\n", "str.replaceFirst(\"a\", \"###\")", str.replaceFirst("a", "###"));
        // 将str中的 “字符串“a”” 全部替换为 “字符串“$$$””
        System.out.printf("%-30s = %s\n", "str.replace(\"a\", \"$$$\")", str.replace("a", "$$$"));

        // 4.分割
        // 以“b”作为分隔符，对str进行分割
        String[] splits = str.split("b");
        for (int i=0; i<splits.length; i++) {
            System.out.printf("splits[%d]=%s\n", i, splits[i]);
        }

        System.out.println();
    }


    /**
     * String 中比较相关API演示
     */
    private static void testCompareAPIs() {
        System.out.println("-------------------------------- testCompareAPIs ------------------------------");

        //String str = "abcdefghijklmnopqrstuvwxyz";
        String str = "abcAbcABCabCAbCabc";
        System.out.printf("%s\n", str);

        // 1. 比较“2个String是否相等”
        System.out.printf("%-50s = %b\n",
                "str.equals(\"abcAbcABCabCAbCabc\")",
                str.equals("abcAbcABCabCAbCabc"));

        // 2. 比较“2个String是否相等(忽略大小写)”
        System.out.printf("%-50s = %b\n",
                "str.equalsIgnoreCase(\"ABCABCABCABCABCABC\")",
                str.equalsIgnoreCase("ABCABCABCABCABCABC"));

        // 3. 比较“2个String的大小”
        System.out.printf("%-40s = %d\n", "str.compareTo(\"abce\")", str.compareTo("abce"));

        // 4. 比较“2个String的大小(忽略大小写)”
        System.out.printf("%-40s = %d\n", "str.compareToIgnoreCase(\"ABC\")", str.compareToIgnoreCase("ABC"));

        // 5. 字符串的开头是不是"ab"
        System.out.printf("%-40s = %b\n", "str.startsWith(\"ab\")", str.startsWith("ab"));

        // 6. 字符串的从位置3开头是不是"ab"
        System.out.printf("%-40s = %b\n", "str.startsWith(\"Ab\")", str.startsWith("Ab", 3));

        // 7. 字符串的结尾是不是"bc"
        System.out.printf("%-40s = %b\n", "str.endsWith(\"bc\")", str.endsWith("bc"));

        // 8. 字符串的是不是包含"ABC"
        System.out.printf("%-40s = %b\n", "str.contains(\"ABC\")", str.contains("ABC"));

        // 9. 比较2个字符串的部分内容
        String region1 = str.substring(2, str.length());    // 获取str位置3(包括)到末尾(不包括)的子字符串
        // 将“str中从位置2开始的字符串”和“region1中位置0开始的字符串”进行比较，比较长度是5。
        System.out.printf("regionMatches(%s) = %b\n", region1,
                str.regionMatches(2, region1, 0, 5));

        // 10. 比较2个字符串的部分内容(忽略大小写)
        String region2 = region1.toUpperCase();    // 将region1转换为大写
        String region3 = region1.toLowerCase();    // 将region1转换为小写
        System.out.printf("regionMatches(%s) = %b\n", region2,
                str.regionMatches(2, region2, 0, 5));
        System.out.printf("regionMatches(%s) = %b\n", region3,
                str.regionMatches(2, region3, 0, 5));

        // 11. 比较“String”和“StringBuffer”的内容是否相等
        System.out.printf("%-60s = %b\n",
                "str.contentEquals(new StringBuffer(\"abcAbcABCabCAbCabc\"))",
                str.contentEquals(new StringBuffer("abcAbcABCabCAbCabc")));

        // 12. 比较“String”和“StringBuilder”的内容是否相等
        System.out.printf("%-60s = %b\n",
                "str.contentEquals(new StringBuilder(\"abcAbcABCabCAbCabc\"))",
                str.contentEquals(new StringBuilder("abcAbcABCabCAbCabc")));

        // 13. match()测试程序
        // 正则表达式 xxx.xxx.xxx.xxx，其中xxx中x的取值可以是0～9，xxx中有1～3位。
        String reg_ipv4 = "[0-9]{3}(\\.[0-9]{1,3}){3}";

        String ipv4addr1 = "192.168.1.102";
        String ipv4addr2 = "192.168";
        System.out.printf("%-40s = %b\n", "ipv4addr1.matches()", ipv4addr1.matches(reg_ipv4));
        System.out.printf("%-40s = %b\n", "ipv4addr2.matches()", ipv4addr2.matches(reg_ipv4));

        System.out.println();
    }

    /**
     * String 的valueOf()演示程序
     */
    private static void testValueAPIs() {
        System.out.println("-------------------------------- testValueAPIs --------------------------------");
        // 1. String    valueOf(Object obj)
        //  实际上，返回的是obj.toString();
        HashMap map = new HashMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        System.out.printf("%-50s = %s\n", "String.valueOf(map)", String.valueOf(map));

        // 2.String    valueOf(boolean b)
        System.out.printf("%-50s = %s\n", "String.valueOf(true)", String.valueOf(true));

        // 3.String    valueOf(char c)
        System.out.printf("%-50s = %s\n", "String.valueOf('m')", String.valueOf('m'));

        // 4.String    valueOf(int i)
        System.out.printf("%-50s = %s\n", "String.valueOf(96)", String.valueOf(96));

        // 5.String    valueOf(long l)
        System.out.printf("%-50s = %s\n", "String.valueOf(12345L)", String.valueOf(12345L));

        // 6.String    valueOf(float f)
        System.out.printf("%-50s = %s\n", "String.valueOf(1.414f)", String.valueOf(1.414f));

        // 7.String    valueOf(double d)
        System.out.printf("%-50s = %s\n", "String.valueOf(3.14159d)", String.valueOf(3.14159d));

        // 8.String    valueOf(char[] data)
        System.out.printf("%-50s = %s\n", "String.valueOf(new char[]{'s','k','y'})", String.valueOf(new char[]{'s','k','y'}));

        // 9.String    valueOf(char[] data, int offset, int count)
        System.out.printf("%-50s = %s\n", "String.valueOf(new char[]{'s','k','y'}, 0, 2)", String.valueOf(new char[]{'s','k','y'}, 0, 2));

        System.out.println();
    }

    /**
     * String 中index相关API演示
     */
    private static void testIndexAPIs() {
        System.out.println("-------------------------------- testIndexAPIs --------------------------------");

        String istr = "abcAbcABCabCaBcAbCaBCabc";
        System.out.printf("istr=%s\n", istr);

        // 1. 从前往后，找出‘a’第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.indexOf((int)'a')", istr.indexOf((int)'a'));

        // 2. 从位置5开始，从前往后，找出‘a’第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.indexOf((int)'a', 5)", istr.indexOf((int)'a', 5));

        // 3. 从后往前，找出‘a’第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.lastIndexOf((int)'a')", istr.lastIndexOf((int)'a'));

        // 4. 从位置10开始，从后往前，找出‘a’第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.lastIndexOf((int)'a', 10)", istr.lastIndexOf((int)'a', 10));


        // 5. 从前往后，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.indexOf(\"bc\")", istr.indexOf("bc"));

        // 6. 从位置5开始，从前往后，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.indexOf(\"bc\", 5)", istr.indexOf("bc", 5));

        // 7. 从后往前，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.lastIndexOf(\"bc\")", istr.lastIndexOf("bc"));

        // 8. 从位置4开始，从后往前，找出"bc"第一次出现的位置
        System.out.printf("%-30s = %d\n", "istr.lastIndexOf(\"bc\", 4)", istr.lastIndexOf("bc", 4));

        System.out.println();
    }

    /**
     * String 中与unicode相关的API
     */
    private static void testUnicodeAPIs() {
        System.out.println("-------------------------------- testUnicodeAPIs ------------------------------");

        String ustr = new String(new int[] {0x5b57, 0x7b26, 0x7f16, 0x7801}, 0, 4);  // "字符编码"(\u5b57是‘字’的unicode编码)。0表示起始位置，4表示长度。
        System.out.printf("ustr=%s\n", ustr);

        //  获取位置0的元素对应的unciode编码
        System.out.printf("%-30s = 0x%x\n", "ustr.codePointAt(0)", ustr.codePointAt(0));

        // 获取位置2之前的元素对应的unciode编码
        System.out.printf("%-30s = 0x%x\n", "ustr.codePointBefore(2)", ustr.codePointBefore(2));

        // 获取位置1开始的元素对应的unciode编码
        System.out.printf("%-30s = %d\n", "ustr.offsetByCodePoints(1, 2)", ustr.offsetByCodePoints(1, 2));

        // 获取第0~3个元素之间的unciode编码的个数
        System.out.printf("%-30s = %d\n", "ustr.codePointCount(0, 3)", ustr.codePointCount(0, 3));

        System.out.println();
    }
}
