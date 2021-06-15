package xyz.kail.demo.java.io;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufferMain {

    public static void main(String[] args) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        System.out.println(byteBuffer);

        final byte[] kailByte = "Kail".getBytes(StandardCharsets.UTF_8);
        byteBuffer.put(kailByte);
        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer);
        System.out.println((char) byteBuffer.get());
        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer);
        System.out.println((char) byteBuffer.get());

    }

}
