package xyz.kail.demo.java.bio.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class UdpClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 传入0表示让操作系统分配一个端口号
        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(10000);

            InetAddress host = InetAddress.getByName(UdpConstant.HOSTNAME);
            // 指定包要发送的目的地
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, UdpConstant.PORT);
            // 为接受的数据包创建空间
//            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);

            for (; ; ) {
                socket.send(request);
                TimeUnit.SECONDS.sleep(1);
            }
//            socket.receive(response);
//
//            String result = new String(response.getData(), 0, response.getLength(), UdpConstant.CHARSET);
//            System.out.println(result);


        }
    }

}
