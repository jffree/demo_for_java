package java_demo.network_model.basic_reactor;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

final public class Handler implements Runnable{
    static final char END = '\n';
    static final int READING = 0, SENDING = 1;
    final private SocketChannel socket;
    final private SelectionKey sk;
    final private ByteBuffer input  = ByteBuffer.allocate(1000);
    final private ByteBuffer output = ByteBuffer.allocate(1000);
    private int state = READING;
    private boolean sendComplete = false;

    public Handler(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        socket.configureBlocking(false);
        sk = socket.register(sel, 0);
        sk.attach(this);
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

    public boolean inputIsComplete() throws IOException {
        int readBytes = socket.read(input);
        if(readBytes > 0)
            return true;
        else
            return false;
    }

    public boolean outputIsComplete(){
        return true;
    }

    public void process(){
        input.flip();
        byte[] bytes = new byte[input.limit()];
        input.get(bytes, 0, input.limit());
        input.clear();
        String s = new String(bytes);
        System.out.println(String.format("Server receive data form port %d : %s ", socket.socket().getPort(), s));
        state = SENDING;
        sk.interestOps(SelectionKey.OP_WRITE);
    }

    public void read() throws IOException {
        if(inputIsComplete()){
            process();
        }
    }

    public void send() throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        output.put((df.format(new Date()) + " server send data.\n").getBytes());
        if(outputIsComplete()){
            output.flip();
            socket.write(output);
            output.clear();
            state = READING;
        }
    }
}
