package xyz.kail.demo.java.io.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 * 发送数据
 */
public class UdpSendMain {

    private static final ByteBuffer BUFFER = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();

        for (int i = 0; i < 100; i++) {
            BUFFER.clear();
            BUFFER.put(("DatagramChannel." + i).getBytes(StandardCharsets.UTF_8));

            // 转换为读模式
            BUFFER.flip();
            //
            System.out.println(BUFFER);
            channel.send(BUFFER, new InetSocketAddress("127.0.0.1", 9999));
        }

        // 关闭
        channel.close();
    }

}
