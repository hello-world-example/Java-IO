package xyz.kail.demo.java.io.file;

import xyz.kail.demo.java.io.tool.FileChannelTool;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class FileChannelTransferMain {

    public static void main(String[] args) throws IOException {

        final Path filePath1 = FileChannelTool.getResourcePath(FileChannelTransferMain.class);
        System.out.println(filePath1);
        final Path filePath2 = FileChannelTool.getResourcePath(FileChannelLock2Main.class);
        System.out.println(filePath2);

        final FileChannel fileChannel1 = FileChannel.open(filePath1, CREATE, WRITE, READ);

        final FileChannel fileChannel2 = FileChannel.open(filePath2, CREATE, WRITE, READ);

        fileChannel1.transferTo(10, 10, fileChannel2);

        fileChannel1.close();
        fileChannel2.close();


    }

}
