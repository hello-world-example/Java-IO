package xyz.kail.demo.java.bio.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class UdpServer {

    public static void main(String[] args) throws IOException {

        try (DatagramSocket socket = new DatagramSocket(UdpConstant.PORT)) {
            while (true) {
                try {
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);

                    String daytime = new Date().toString();
                    byte[] data = daytime.getBytes(UdpConstant.CHARSET);
                    DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    socket.send(response);
                    System.out.println(daytime + " " + request.getAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
