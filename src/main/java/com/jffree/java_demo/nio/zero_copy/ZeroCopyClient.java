package com.jffree.java_demo.nio.zero_copy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

class ZeroCopyClient {
    private final static String testFile = "testFile.txt";

    public static void main(String[] args) throws IOException {
        ZeroCopyClient sfc = new ZeroCopyClient();
        sfc.testSendfile();
    }

    public String getFilePathFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(fileName).getPath();
    }

    public void testSendfile() throws IOException {
        String host = "localhost";
        int port = 9026;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        String fname = getFilePathFromResources(testFile);
        FileChannel fc = new FileInputStream(fname).getChannel();
        long start = System.nanoTime();
        long nsent = 0, curnset = 0;
        curnset = fc.transferTo(0, fc.size(), sc);
        System.out.println("发送的总字节数:" + curnset + " 耗时(ns):" + (System.nanoTime() - start));
        try {
            sc.close();
            fc.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
