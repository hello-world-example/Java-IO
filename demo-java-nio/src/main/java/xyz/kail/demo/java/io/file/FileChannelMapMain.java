package xyz.kail.demo.java.io.file;

import xyz.kail.demo.java.io.tool.FileChannelTool;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileChannelMapMain {

    public static void main(String[] args) throws IOException {
        final Path filePath = FileChannelTool.getResourcePath(FileChannelMapMain.class);
        System.out.println(filePath);

        final FileChannel fileChannel = FileChannel.open(filePath, READ, WRITE);

        final MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        System.out.println(mappedByteBuffer);

        mappedByteBuffer.put("Kail".getBytes(StandardCharsets.UTF_8));


        System.out.println(mappedByteBuffer);
        mappedByteBuffer.flip();
        System.out.println(mappedByteBuffer);

        System.out.print((char) mappedByteBuffer.get());
        System.out.print((char) mappedByteBuffer.get());
        System.out.print((char) mappedByteBuffer.get());
        System.out.println((char) mappedByteBuffer.get());

        fileChannel.close();

    }

}
