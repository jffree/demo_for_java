package com.jffree.java_demo.iotream.charStream;

import java.io.*;
import java.util.Scanner;

/**
 * BufferedWriter 测试程序
 *
 * @author skywang
 */
public class BufferedWriterTest {

    private static final int LEN = 5;
    // 对应英文字母“abcdefghijklmnopqrstuvwxyz”
    //private static final char[] ArrayLetters = "abcdefghijklmnopqrstuvwxyz";
    private static final char[] ArrayLetters = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public static void main(String[] args) {
        testBufferedWriter() ;
    }

    /**
     * BufferedWriter的API测试函数
     */
    private static void testBufferedWriter() {

        // 创建“文件输出流”对应的BufferedWriter
        // 它对应缓冲区的大小是16，即缓冲区的数据>=16时，会自动将缓冲区的内容写入到输出流。
        try {
            File file = new File("e:\\github\\test-file\\test.txt");
            BufferedWriter out =
                    new BufferedWriter(
                            new FileWriter(file));

            // 将ArrayLetters数组的前10个字符写入到输出流中
            out.write(ArrayLetters, 0, 10);
            // 将“换行符\n”写入到输出流中
            out.write('\n');

            out.flush();
            //readUserInput() ;

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取用户输入
     */
    private static void readUserInput() {
        System.out.println("please input a text:");
        Scanner reader=new Scanner(System.in);
        // 等待一个输入
        String str = reader.next();
        System.out.printf("the input is : %s\n", str);
    }
}