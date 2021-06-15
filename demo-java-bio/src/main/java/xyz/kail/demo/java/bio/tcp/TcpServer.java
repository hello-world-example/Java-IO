package xyz.kail.demo.java.bio.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TcpServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(18080);

        System.out.println("accepting");
        final Socket accept = serverSocket.accept();
        System.out.println("accepted");

        final InputStream inputStream = accept.getInputStream();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        for (; ; ) {
            System.out.println("reading");
            final String readLine = bufferedReader.readLine();
            System.out.println("read:" + readLine);
            System.out.println("print" + LocalDateTime.now());

            TimeUnit.SECONDS.sleep(1);

            accept.sendUrgentData(0);
        }


    }

}
