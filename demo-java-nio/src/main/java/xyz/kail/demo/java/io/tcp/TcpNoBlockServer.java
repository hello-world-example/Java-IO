package xyz.kail.demo.java.io.tcp;

import xyz.kail.demo.java.io.tool.SelectKeyTool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class TcpNoBlockServer {

    private static final ByteBuffer BUFFER = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        //
        //
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println("serverSocketChannel:" + serverSocketChannel.hashCode());
        // 默认是 阻塞模式
        // 设置非阻塞模式，否则注册时会报：java.nio.channels.IllegalBlockingModeException
        serverSocketChannel.configureBlocking(false);
        // 绑定端口
        // JDK 1.7 前，需要调用 ServerSocketChannel 的 socket() 方法，再调用 bind() 来进行关联
        // JDK 1.7 后，就可以直接调用 ServerSocketChannel 的 bind() 来进行端口绑定了
        serverSocketChannel.bind(new InetSocketAddress(8899));
        // 当前 Channel 注册到 selector 上，
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, "port:8899:" + new Date());

        while (true) {
            int readyChannels = selector.select(1000);
            // 没有一个通道就绪
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                try {
                    doSelectionKey(selector, selectionKey);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    keyIterator.remove();
                }
            }
        }


    }

    private static void doSelectionKey(Selector selector, SelectionKey selectionKey) throws IOException {
        SelectKeyTool.printStatus(selectionKey);


        // 某个 Channel 成功连接到服务器，一个新的链接产生
        if (selectionKey.isAcceptable()) {
            // 与注册的 serverSocketChannel 是一个实例
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            // 获取 客户端 Socket Channel
            SocketChannel clientSocketChannel = serverSocketChannel.accept();
            // 设置非阻塞模式
            clientSocketChannel.configureBlocking(false);
            clientSocketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, "acc:" + new Date());
            return;
        }

        //
        //
        // 准备好接收新进入的连接称为“接收就绪”
        if (selectionKey.isConnectable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            BUFFER.clear();
            //
            StringBuilder msg = new StringBuilder();
            // 将客户端发送过来的数据，从管道中读取到或者说写到 接收缓存里
            while (socketChannel.read(BUFFER) > 0) {
                //
                BUFFER.flip();
                //
                msg.append(new String(BUFFER.array()));
                // 清除数据，下次可以重新写入
                BUFFER.clear();
            }
            //
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, "conn:" + new Date());
            // 打印输出从客户端读取到的信息
            System.out.println("------>:\t" + msg.toString());
            return;
        }

        // 接收客户端数据
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            BUFFER.clear();
            final int read = socketChannel.read(BUFFER);
            // 客户端关闭了
            if (-1 == read) {
                selectionKey.cancel();
                socketChannel.close();
                return;
            }
            System.out.println(new String(BUFFER.array(), 0, BUFFER.position()));
            selectionKey.interestOps(SelectionKey.OP_WRITE);
            return;
        }
        //
        //
        // 向客户端 发送数据
        if (selectionKey.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            BUFFER.clear();
            BUFFER.put("Hello Server\n".getBytes(StandardCharsets.UTF_8));
            BUFFER.flip();
            socketChannel.write(BUFFER);
            selectionKey.interestOps(SelectionKey.OP_READ);
        }

    }

}
