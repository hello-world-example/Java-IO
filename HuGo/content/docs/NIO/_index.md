# NIO 概述



## 核心组件

### Channels and Buffers（通道和缓冲区）

标准的 IO 基于 字节流 和 字符流 进行操作的，而 NIO 是基于 通道（Channel）和缓冲区（Buffer）进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。



### Non-blocking IO（非阻塞IO）

Java NIO 可以非阻塞的使用IO，例如：当线程从通道读取数据到缓冲区时，线程还是可以进行其他事情。当数据被写入到缓冲区时，线程可以继续处理它。从缓冲区写入通道也类似。



### Selectors（选择器）

Java NIO 引入了选择器的概念，选择器用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单个的线程可以监听多个数据通道。



## Channel

下面是 Java NIO 中的一些主要 Channel 的实现：

- `FileChannel`：从文件中读写数据。
- `DatagramChannel`：能通过 UDP 读写网络中的数据。
- `SocketChannel`：能通过 TCP 读写网络中的数据。
- `ServerSocketChannel`：可以监听新进来的 TCP 连接，像Web服务器那样。对每一个新进来的连接都会创建一个`SocketChannel`



## Buffer

以下是 Java NIO 里关键的 Buffer 实现：

- `ByteBuffer`
- CharBuffer
- DoubleBuffer
- FloatBuffer
- IntBuffer
- LongBuffer
- ShortBuffer

这些Buffer覆盖了你能通过 IO 发送的基本数据类型：byte, short, int, long, float, double 和 char，没有 boolean。

Java NIO 还有个 `MappedByteBuffer`，用于表示内存映射文件。



## Selector

Selector 允许单线程处理多个 Channel。如果你的应用打开了多个连接（通道），但每个连接的流量都很低，使用Selector就会很方便。

要使用Selector，得向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新连接进来，数据接收等。



## Read More

- 原文： [Java NIO系列教程（一） Java NIO 概述](http://ifeve.com/overview/)
