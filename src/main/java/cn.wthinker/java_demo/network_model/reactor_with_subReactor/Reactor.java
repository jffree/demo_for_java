package cn.wthinker.java_demo.network_model.reactor_with_subReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Ref: Scalable IO in Java
 */

public class Reactor extends Thread {
    protected Selector           selector;
    private static AtomicInteger id = new AtomicInteger();
    private int                  reactorId;

    public Reactor() throws IOException {
        selector = Selector.open();
        reactorId = id.getAndIncrement();
    }

    public Selector getSelector() {
        return selector;
    }

    void dispatch(SelectionKey sk) {
        Runnable r = (Runnable) (sk.attachment());
        if (r != null)
            r.run();
    }

    @Override
    public void run(){
        try{
            System.out.println(String.format("Start reactor %d !", reactorId));
            while (!Thread.interrupted()){
                selector.selectNow();
                Set<SelectionKey> selected = selector.selectedKeys();
                selected.forEach(sk -> dispatch((SelectionKey) sk));
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                selector.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
