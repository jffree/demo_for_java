package com.jffree.java_demo.nio.zero_copy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MappedByteBufferTest {
    private final static String     fileName = "testFile.txt";
    private final static CharBuffer cache    = CharBuffer.wrap("This is a content of the file.");
    private RandomAccessFile        randomAccessFile;
    private FileChannel             fc;
    private MappedByteBuffer        mappedByteBuffer;

    public String getFilePathFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(fileName).getPath();
    }

    @Before
    public void init() {
        String path = getFilePathFromResources(fileName);
        File file = new File(path);
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            fc = randomAccessFile.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTest() {
        try {
            Assert.assertNotNull(fc);
            mappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            if (mappedByteBuffer != null) {
                Assert.assertFalse(mappedByteBuffer.isLoaded());
                System.out.println(mappedByteBuffer.capacity());
                CharBuffer charBuffer = null;
                charBuffer = StandardCharsets.UTF_8.decode(mappedByteBuffer);
                if (charBuffer != null) {
                    System.out.println(charBuffer);
                }
                Assert.assertEquals(charBuffer, cache);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeTest() {
        try {
            Assert.assertNotNull(fc);
            mappedByteBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, cache.capacity());
            if (mappedByteBuffer != null) {
                mappedByteBuffer.put(Charset.forName("utf-8").encode(cache));
                mappedByteBuffer.force();
            }
            Assert.assertEquals(cache.capacity(), mappedByteBuffer.capacity());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clean() {
        try {
            Assert.assertNotNull(mappedByteBuffer);
            Method cleanMethod = mappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
            cleanMethod.setAccessible(true);
            sun.misc.Cleaner cleaner = (sun.misc.Cleaner) cleanMethod.invoke(mappedByteBuffer, new Object[0]);
            cleaner.clean();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        if (fc != null) {
            try {
                clean();
                fc.close();
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
