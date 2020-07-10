# IO STREAM

1. 在 `java.io` 包中操作文件内容的主要有两大类：字节流（一个字节为单位）、字符流（两个字节为单位），两类都分为输入和输出操作。

2. 在字节流中输出数据主要是使用 `OutputStream` 完成，输入使的是 `InputStream`，字节流一般以 IO 对象为参数构造。

3. 在字符流中输出主要是使用 `Writer` 类完成，输入流主要使用 `Reader` 类完成，字符流一般以字节流对象为参数构造。

4. PipedInputStream运用的是一个数组作为数据缓冲区（默认大小为 1024 字节），并不是真正意义上的管道。写入PipedOutputStream的数据实际上保存到对应的 PipedInputStream的内部缓冲区。
   从PipedInputStream执行读操作时，读取的数据实际上来自这个内部缓冲区。如果对应的 PipedInputStream输入缓冲区已满，任何企图写入PipedOutputStream的线程都将被阻塞。
   而且这个写操作线程将一直阻塞，直至出现读取PipedInputStream的操作从缓冲区删除数据。


## 类层次图

![输入流和输出流的类层次图](https://images2017.cnblogs.com/blog/663847/201801/663847-20180107231342065-2108564502.png)

## 参考

* [Java总结：Java 流(Stream)、文件(File)和IO](https://www.cnblogs.com/52fhy/p/8232825.html)

* [java io系列01之 "目录"](https://www.cnblogs.com/skywang12345/p/io_01.html)