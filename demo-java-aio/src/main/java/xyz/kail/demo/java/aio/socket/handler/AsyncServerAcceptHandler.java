package xyz.kail.demo.java.aio.socket.handler;

import xyz.kail.demo.java.aio.socket.AsyncAttachment;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AsyncServerAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncAttachment> {

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, AsyncAttachment attachment) {
        // 当前链接接收成功后，接收下一个链接
        attachment.getAsyncServerSocketChannel().accept(
                new AsyncAttachment(attachment.getAsyncServerSocketChannel()), this //
        );

        // 写入附加信息
        attachment.setAsyncSocketChannel(socketChannel);
        attachment.setByteBuffer(ByteBuffer.allocate(1024));
        // 处理当前链接
        socketChannel.read(attachment.getByteBuffer(), attachment, new AsyncServerReadHandler());
    }

    @Override
    public void failed(Throwable exc, AsyncAttachment attachment) {
        exc.printStackTrace();
    }
}
