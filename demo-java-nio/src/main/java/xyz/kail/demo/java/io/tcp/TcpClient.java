package xyz.kail.demo.java.io.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class TcpClient {

    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        //
        //
        final SocketChannel socketChannel = SocketChannel.open();
        //
//        socketChannel.configureBlocking(false);
        //
        socketChannel.connect(new InetSocketAddress(8899));

        final ByteBuffer byteBuffer = ByteBuffer.wrap("123".getBytes(StandardCharsets.UTF_8));
        socketChannel.write(byteBuffer);

        socketChannel.close();


    }

}
