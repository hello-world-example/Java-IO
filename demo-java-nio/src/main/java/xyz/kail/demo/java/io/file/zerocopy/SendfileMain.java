package xyz.kail.demo.java.io.file.zerocopy;

import xyz.kail.demo.java.io.tool.FileChannelTool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.READ;

public class SendfileMain {

    public static void main(String[] args) throws IOException {
        final Path filePath = FileChannelTool.getResourcePath(SendfileMain.class);

        //
        final FileChannel fileChannel = FileChannel.open(filePath, READ);

        //
        // 通过 UDP 发送出去
        final DatagramChannel channel = DatagramChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 9999));

        // sendfile
        fileChannel.transferTo(0, 1024, channel);

    }

}
