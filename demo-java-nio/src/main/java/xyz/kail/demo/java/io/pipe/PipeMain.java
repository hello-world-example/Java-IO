package xyz.kail.demo.java.io.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

public class PipeMain {

    public static void main(String[] args) throws IOException {
        final Pipe pipe = Pipe.open();

        final Pipe.SinkChannel sinkChannel = pipe.sink();
        final Pipe.SourceChannel sourceChannel = pipe.source();

        sinkChannel.write(ByteBuffer.wrap("Kail".getBytes(StandardCharsets.UTF_8)));


        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        sourceChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.position()));
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));

    }

}
