package xyz.kail.demo.java.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by kail on 2018/5/27.
 */
public class HttpSocketMain {

    public static void main(String[] args) throws IOException {
        // 监听本地的 nginx
        Socket socket = new Socket("www.baidu.com", 80);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream);

        // 请求的第一行Request-Line，需要写请求的URL(/)
        pw.println("GET / HTTP/1.1");
        // 请求头，Host是必须的
        // pw.println("Host: localhost");
        pw.println("Host: www.baidu.com");
        // 响应结束后关闭链接
        // https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Connection
        pw.println("Connection: close");
        // 一定要有个空行表示请求结束
        pw.println();
        // 提交请求
        pw.flush();


        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        // 输出响应内容（如果 Connection: close 不指定，这个读取完相应后会阻塞）
        for (String readLine; null != (readLine = bufferedReader.readLine()); ) {
            System.out.println(readLine);
        }

        // 关闭资源
        close(pw, outputStream, socket, bufferedReader, reader, inputStream);
    }


    /**
     * 静默关闭资源（实际上并不严格，可以使用 try-with-resources 结构）
     */
    private static void close(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                // noting
            }
        }

    }


}
