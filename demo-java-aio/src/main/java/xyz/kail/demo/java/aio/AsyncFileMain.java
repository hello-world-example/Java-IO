package xyz.kail.demo.java.aio;

import xyz.kail.demo.java.io.tool.FileChannelTool;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class AsyncFileMain {

    public static void main(String[] args) throws IOException {

        final Path resourcePath = FileChannelTool.getResourcePath(AsyncFileMain.class);

        final AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(resourcePath, CREATE, READ, WRITE);

        final ByteBuffer data = ByteBuffer.wrap("I am AsynchronousFileChannel".getBytes(StandardCharsets.UTF_8));

        fileChannel.write(data, 0);

        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer, 0, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("read end !!");
                System.out.println(new String(byteBuffer.array(), 0, result));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });
        System.out.println("reading..");

        // 等待读取完成
        System.in.read();

    }

}
