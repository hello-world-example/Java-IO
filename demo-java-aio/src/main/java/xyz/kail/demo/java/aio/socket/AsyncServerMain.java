package xyz.kail.demo.java.aio.socket;

import xyz.kail.demo.java.aio.socket.handler.AsyncServerAcceptHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.Executors;

public class AsyncServerMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        // 创建一个Group，类似于一个线程池，用于处理 IO完成事件
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 32);

        // 开启一个AsynchronousServerSocketChannel，在 8899 端口上监听
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(group);
        serverChannel.bind(new InetSocketAddress(8899));

        // 接收到新连接
        serverChannel.accept(new AsyncAttachment(serverChannel), new AsyncServerAcceptHandler());

        //
        System.in.read();
    }

}
