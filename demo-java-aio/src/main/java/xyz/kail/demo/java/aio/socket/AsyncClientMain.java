package xyz.kail.demo.java.aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class AsyncClientMain {

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException, ExecutionException {

        // 创建一个Group，类似于一个线程池，用于处理 IO完成事件
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 32);

        final AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open(group);

        final Future<Void> connectFuture = socketChannel.connect(new InetSocketAddress(8899));

        System.out.println(System.currentTimeMillis() + ": 等待连接");
        connectFuture.get(500, TimeUnit.MILLISECONDS);
        System.out.println(System.currentTimeMillis() + ": 链接成功");

        socketChannel.write(ByteBuffer.wrap("I am coming".getBytes(StandardCharsets.UTF_8)));

    }

}
