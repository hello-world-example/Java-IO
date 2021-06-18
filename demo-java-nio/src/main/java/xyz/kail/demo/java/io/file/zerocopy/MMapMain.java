package xyz.kail.demo.java.io.file.zerocopy;

import xyz.kail.demo.java.io.tool.FileChannelTool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.MappedByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.READ;

public class MMapMain {

    public static void main(String[] args) throws IOException {
        final Path filePath = FileChannelTool.getResourcePath(MMapMain.class);

        //
        final FileChannel fileChannel = FileChannel.open(filePath, READ);
        // memory-mapped
        final MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 1024);
        //
        // 通过 UDP 发送出去
        final DatagramChannel channel = DatagramChannel.open();
        channel.send(buffer, new InetSocketAddress("127.0.0.1", 9999));

    }

}
