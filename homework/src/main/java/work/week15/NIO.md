NIO（Non-blocking I/O，在Java领域，也称为New I/O），是一种同步非阻塞的I/O模型。NIO一个重要的特点是：socket主要的读、写在等待阶段都是非阻塞的，性能非常高。  
Netty是一款基于NIO开发的网络通信框架，具有高吞吐、低延迟、低开销、零拷贝、可扩容、松耦合、使用方便、可维护性好的特性。Channel：通道，Java NIO 中的基础概念，代表一个打开的连接，可执行读取/写入 IO 操作。  
ChannelFuture：Java 的 Future 接口，只能查询操作的完成情况，或者阻塞当前线程等待操作完成。Netty封装一个 ChannelFuture 接口。我们可以将回调方法传给 ChannelFuture，在操作完成时自动执行。  
Event & Handler：Netty 基于事件驱动，事件和处理器可以关联到入站和出站数据流。  
Encoder、Decoder：处理网络 IO 时，需要进行序列化和反序列化，转换 Java 对象与字节流。 对入站数据进行解码，基类是 ByteToMessageDecoder。 对出站数据进行编码，基类是 MessageToByteEncoder。   
 ChannelPipeline：数据处理管道就是事件处理器链。有顺序、同一 Channel 的出站处理器和入站处理器在同一个列表中。