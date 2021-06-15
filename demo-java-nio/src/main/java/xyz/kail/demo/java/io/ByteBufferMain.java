package xyz.kail.demo.java.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ByteBufferMain {

    public static void main(String[] args) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(20);

        final IntBuffer intBuffer = IntBuffer.allocate(100);
    }

}
