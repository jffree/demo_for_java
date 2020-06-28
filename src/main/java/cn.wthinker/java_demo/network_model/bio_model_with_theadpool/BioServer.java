package cn.wthinker.java_demo.network_model.bio_model_with_theadpool;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class BioServer extends Thread {
    private int                port;
    private ServerSocket       server;
    private ThreadPoolExecutor executor;

    static class RequestHandler implements Runnable {
        private Socket         socket;
        private PrintWriter    writer;
        private BufferedReader reader;

        public RequestHandler(Socket socket) throws IOException {
            this.socket = socket;
            writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                while (true) {
                    String s = reader.readLine();
                    if (s == null)
                        break;
                    System.out.println(String.format("Server receive data form port %d : %s ", socket.getPort(), s));
                    writer.println(df.format(new Date()) + " server send data.");
                    writer.flush();
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("socket closed!");
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BioServer(int port) throws IOException {
        this.port = port;
        server = new ServerSocket();
        server.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
        executor = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(4));
    }

    @Override
    public void run() {
        System.out.println("Server run on port: " + this.port);
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Server receive new connect from port: " + socket.getPort());
                executor.execute(new RequestHandler(socket));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            BioServer server = new BioServer(6211);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
