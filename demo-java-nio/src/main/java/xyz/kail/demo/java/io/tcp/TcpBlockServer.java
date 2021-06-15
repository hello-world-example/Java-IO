package xyz.kail.demo.java.io.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * nc 127.0.0.1 8899
 */
public class TcpBlockServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 服务器端，通过 open 方法，来创建 ServerSocketChannel
        // 注意，此时，服务器端，还没有进行绑定端口呢
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 默认就是阻塞模式
        serverSocketChannel.configureBlocking(true);

        //绑定端口号
        //JDK1.7版本之后的写法
        serverSocketChannel.bind(new InetSocketAddress(8899));
        //JDK1.7版本之前的写法
        // serverSocketChannel.socket().bind(new InetSocketAddress(8081));

        // 创建字节缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            System.out.println("-------服务器端-----开始接收-----客户端的连接---------");
            // 在服务器端，接收客户端的链接，如果存在客户端的话，就返回一个
            // SocketChannel 对象
            // 如果是阻塞模式的话，没有新的链接进来，就会阻塞在这里，否则，往下执行
            // 如果是非阻塞模式的话，没有新的链接进来，就会立马返回一个null，程序不会阻塞在这里，会立马往下进行的
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (null == socketChannel) {
                Thread.sleep(1000);
                continue;
            }

            while (true) {
                // 清除缓存区的数据，可以接收新的数据
                byteBuffer.clear();

                // 将管道 socketChannel 的数据读取到 Buffer 中
                // readSize 表示 读取的字节数
                int readSize = socketChannel.read(byteBuffer);
                if (readSize == -1) {
                    break;
                }
                // 再从 字节缓存里，进行其他 业务逻辑操作
                // 注意，这里的缓存区使用的字节类型
                // 因此，如果需要其他类型的话，需要进行转换
                System.out.println(new String(byteBuffer.array(), 0, readSize));
            }
        }
    }

}
