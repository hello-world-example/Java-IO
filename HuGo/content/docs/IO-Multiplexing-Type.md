# IO 多路复用技术



- `select`、 `poll`、`epoll` 都是 **阻塞调用**
- **多路**：多个 Socket 套接字
- **复用**：同一个进程、线程



## select（O<sub>(n)</sub>）

```c
// 当 select 函数返回后，可以通过遍历 fdset，来找到就绪的描述符
int select(
  int nfds, 
  fd_set *readfds, 
  fd_set *writefds, 
  fd_set *exceptfds, 
  struct timeval *timeout // 等待时间，如果立即返回设为 null
)
```



- select 最大的缺陷就是**打开的 FD 是有一定限制的**
  - 可以通过多进程的方式突破限制（Apached），但是仍然有以下问题
- 扫描时是**线性扫描，即采用轮询的方法，效率较低**
- 需要把 FD 集合**从用户态拷贝到内核态**，在 FD 很多时会开销很大

> - [[关于 select 中 fd 限制问题](https://www.cnblogs.com/yeta/p/3616728.html)] → `define __FD_SETSIZE  1024`
> - [[为什么select打开的FD数量有限制，而poll、epoll等打开的FD数量没有限制](https://www.zhihu.com/question/37219281/answer/74003967)]



## poll（O<sub>(n)</sub>）

- **基于链表来存储，没有最大连接数的限制**
- `select` 和 `poll` 都需要在返回后，通过遍**历文件描述符来获取已经就绪的 socket**，随着监视的描述符数量的增长，其**效率也会线性下降**
- 需要将用户传入的**数组拷贝到内核空间，然后查询每个fd对应的设备状态**，不管这样的复制是不是有意义



## epoll（O<sub>(1)</sub>）

- 在Linux 内核 2.6 中提出，是之前的 select 和 poll 的增强版本，**能处理大批量句柄**
- **JDK1.5_update10 版本使用 epoll 替代了传统的 select/poll**，极大的提升了NIO通信的性能



### 水平触发 LT(Level Triggered)

- 默认工作方式
- 支持 Block 和 No-Block Socket
- 内核告诉你一个文件描述符是否就绪了，然后你可以对这个就绪的 fd 进行 IO 操作
- **如果你不作任何操作，内核还是会继续通知**
- 这种模式编程出错误可能性要小一点，传统的 select/poll 都是这种模型的代表



### 边缘触发 ET (Edge Triggered)

- 高速工作方式，**只支持 No-Block Socket**
- 在这种模式下，当描述符从未就绪变为就绪时，内核会进行通知
- 但是然后它会假设你知道文件描述符已经就绪，**不会再为那个文件描述符发送更多的就绪通知（only once）**，直到你做了某些操作导致那个文件描述符不再为就绪状态了



### epoll 优点

1. **没有最大并发连接的限制**，能打开的 FD 的上限远大于 1024
2. **效率提升，不是轮询的方式**，不会随着 FD 数目的增加效率下降，只有活跃可用的 FD 才会调用 `callback` 函数
   - 即最大的优点就在于它只管 **活跃** 的连接，而跟连接总数无关，效率就会远远高于 `select` 和 `poll`
3. **内存拷贝**，利用 `mmap()` 文件映射内存加速与内核空间的消息传递，减少复制开销



## kqueue

- 也是 **I/O 多路复用技术** 的一种
- `select`、 `poll`、 `epoll` 为 Linux 独占
- `kqueue` 则在许多UNIX系统上存在，包括 MacOS



## Read More

- [[见识一下 linux 高性能网络 IO+Reactor 模型 ](https://juejin.cn/post/6892687008552976398)]
- [[NIO相关基础篇三](https://mp.weixin.qq.com/s/5SKgdkC0kaHN495psLd3Tg)]

