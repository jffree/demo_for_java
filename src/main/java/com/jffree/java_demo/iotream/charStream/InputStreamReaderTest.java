package com.jffree.java_demo.iotream.charStream;

import java.io.*;

public class InputStreamReaderTest {

    public static void main(String[] args) {
        File in = new File("e:\\github\\test.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(in);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
            char[] data = new char[512];
            int c = -1;
            while ((c = inputStreamReader.read()) != -1){
                System.out.println((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
