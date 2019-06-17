package java_demo.network_model.basic_reactor;

import java.io.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

final public class Handler implements Runnable{
    final private SocketChannel socket;
    final private SelectionKey sk;
    final private PrintWriter writer;
    final private BufferedReader reader;
    static final int READING = 0, SENDING = 1;
    int state = READING;

    public Handler(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        socket.configureBlocking(false);
        writer = new PrintWriter(new OutputStreamWriter(this.socket.socket().getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(this.socket.socket().getInputStream()));
        sk = socket.register(sel, 0);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    @Override
    public void run(){
        try {
            if(state == READING)
                read();
            else if (state == SENDING)
                send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            String s = reader.readLine();
            writer.println(String.format("Server receive data form port %d : %s ", socket.socket().getPort(), s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        state = SENDING;
        sk.interestOps(SelectionKey.OP_WRITE);
    }

    public void send() throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        writer.println(df.format(new Date())+ " server send data.\n");
        state = READING;
        sk.interestOps(SelectionKey.OP_READ);
    }

}
