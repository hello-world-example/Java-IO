package xyz.kail.demo.java.io.file;

import xyz.kail.demo.java.io.tool.FileChannelTool;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class FileChannelSimpleMain {

    public static void main(String[] args) throws IOException {
        final Path resourcePath = FileChannelTool.getResourcePath(FileChannelSimpleMain.class);

        final FileChannel fileChannel = FileChannel.open(resourcePath, CREATE, READ, WRITE);

        fileChannel.write(ByteBuffer.wrap("Kail".getBytes(StandardCharsets.UTF_8)));

        fileChannel.close();


    }

}
