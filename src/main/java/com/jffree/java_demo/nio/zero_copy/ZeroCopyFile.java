package com.jffree.java_demo.nio.zero_copy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * disk-disk零拷贝
 */
public class ZeroCopyFile {
    private final static String inFile  = "inFile.txt";
    private final static String outFile = "outFile.txt";
    private FileChannel         in;
    private FileChannel         out;

    public String getFilePathFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(fileName).getPath();
    }

    @Before
    public void init() {
        try {
            in = new RandomAccessFile(getFilePathFromResources(inFile), "rw").getChannel();
            out = new RandomAccessFile(getFilePathFromResources(outFile), "rw").getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferToTest() {
        Assert.assertNotNull(in);
        Assert.assertNotNull(out);
        long position = 0;
        long count = 0;
        try {
            count = in.size();
            long size = in.transferTo(position, count, out);
            System.out.println(String.format("TransferTo size: %d", size));
            //读取测试(in 写入到 out， 使得  out 被截断，需要重新打开)
            out.close();
            out = new RandomAccessFile(getFilePathFromResources(outFile), "rw").getChannel();
            ByteBuffer buf = ByteBuffer.allocate(100);
            int bytesRead = out.read(buf);
            Assert.assertEquals(count, bytesRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferFromTest() {
        long position = 0;
        long count = 0;
        try {
            count = in.size();
            long size = out.transferFrom(in, position, count);
            System.out.println(String.format("TransferFrom size: %d", size));
            ByteBuffer buf = ByteBuffer.allocate(100);
            int bytesRead = out.read(buf);
            Assert.assertEquals(count, bytesRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        Assert.assertNotNull(in);
        Assert.assertNotNull(out);
        try {
            in.close();
            out.close();
            System.out.println("Close file channel.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
