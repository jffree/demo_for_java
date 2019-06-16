package java_demo.network_model.bio_model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BioClient extends Thread{
    private static AtomicInteger id = new AtomicInteger();
    private int clientId;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public BioClient(int server_port) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), 6211);
        clientId = id.getAndIncrement();
        System.out.println(String.format("Start client %d on port: %d", clientId, socket.getLocalPort()));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while(true){
                writer.println(String.format("%s client %d send data.", df.format(new Date()), clientId));
                writer.flush();
                String str = reader.readLine();
                System.out.println(String.format("Client %d received: %s", clientId, str));
                Thread.sleep(1000);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        IntStream.range(1,5).forEach(i -> {
           try {
            new BioClient(6211).start();
        } catch (IOException e) {
               e.printStackTrace();
           }
        });
    }


}
