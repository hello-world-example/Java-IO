package xyz.kail.demo.java.aio.socket.handler;

import xyz.kail.demo.java.aio.socket.AsyncAttachment;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AsyncServerReadHandler implements CompletionHandler<Integer, AsyncAttachment> {

    /**
     * 读取完成事件的处理函数
     */
    @Override
    public void completed(Integer size, AsyncAttachment attachment) {
        System.out.println(size);

        final ByteBuffer byteBuffer = attachment.getByteBuffer();

        final AsynchronousSocketChannel asyncSocketChannel = attachment.getAsyncSocketChannel();

        if (size == -1) {
            System.out.println("客户端没 close");
            try {
                asyncSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.position()));
        byteBuffer.clear();

        // 继续读取
        asyncSocketChannel.read(byteBuffer, attachment, this);


    }

    @Override
    public void failed(Throwable exc, AsyncAttachment attachment) {
        exc.printStackTrace();
    }
}
