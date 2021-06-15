package xyz.kail.demo.java.bio.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TcpClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 18080);

        final OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);


        for (int i = 0; i < 60; i++) {
            outputStreamWriter.write(i + ": send :" + System.lineSeparator());
            outputStreamWriter.flush();

            System.out.println("send:" + i);

            TimeUnit.SECONDS.sleep(5);
        }

        outputStream.close();
        socket.close();

    }

}
