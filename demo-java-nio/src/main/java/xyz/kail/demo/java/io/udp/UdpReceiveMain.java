package xyz.kail.demo.java.io.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 * 接收数据
 * <p>
 * echo -n '.{ "version": "1.1", "host": "example.org", "short_message": "A short message", "level": 5, "_some_info": "foo 中文" }' | nc -w0 -u  localhost 9999
 */
public class UdpReceiveMain {

    private static final ByteBuffer BUFFER = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(9999));

        final DatagramSocket datagramSocket = channel.socket();
        datagramSocket.setBroadcast(true);


        for (; ; ) {
            BUFFER.clear();

            // 接收到的数据包内容复制到指定的 Buffer
            // 如果 Buffer 容不下收到的数据，多出的数据将被丢弃
            channel.receive(BUFFER);

            final byte[] array = BUFFER.array();

            System.out.println(BUFFER);
            final String content = new String(array, 0, BUFFER.position(), StandardCharsets.UTF_8);
            System.out.println(content);

            //
            if (content.startsWith(".")) {
                break;
            }
        }

        // 关闭
        channel.close();
    }

}
