package com.jffree.java_demo.iotream.byteStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamTest {
    public static void main(String[] args) {
        int size;
        InputStream fis = null;

        try {
            fis = new FileInputStream("e:\\github\\test-file\\test.txt");
            try {
                size = fis.available();
                System.out.println("可读取的字节数 " + size);
                char[] text = new char[size];
                for (int i = 0; i < text.length; i++) {
                    text[i] = ((char) fis.read());
                    System.out.print(text[i]);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
