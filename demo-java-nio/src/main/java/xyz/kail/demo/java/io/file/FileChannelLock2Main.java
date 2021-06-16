package xyz.kail.demo.java.io.file;

import xyz.kail.demo.java.io.tool.FileChannelTool;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileChannelLock2Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        final Path resourcePath = FileChannelTool.getResourcePath(FileChannelLockMain.class);

        final FileChannel fileChannel = FileChannel.open(resourcePath, CREATE, WRITE);
        System.out.println("locking");
        try (final FileLock lock = fileChannel.lock()) {
            System.out.println("locked");
            TimeUnit.SECONDS.sleep(10);
        }
        System.out.println("unlock");
        fileChannel.close();


    }

}
