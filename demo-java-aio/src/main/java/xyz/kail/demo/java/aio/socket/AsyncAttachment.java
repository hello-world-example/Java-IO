package xyz.kail.demo.java.aio.socket;


import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

@Getter
@Setter
public class AsyncAttachment {

    public AsyncAttachment(AsynchronousServerSocketChannel asyncServerSocketChannel) {
        this.asyncServerSocketChannel = asyncServerSocketChannel;
    }

    private AsynchronousServerSocketChannel asyncServerSocketChannel;

    private AsynchronousSocketChannel asyncSocketChannel;

    private ByteBuffer byteBuffer;


}
