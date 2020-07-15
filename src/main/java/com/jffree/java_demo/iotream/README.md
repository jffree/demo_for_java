# IO STREAM

1. 在 `java.io` 包中操作文件内容的主要有两大类：字节流（一个字节为单位）、字符流（两个字节为单位），两类都分为输入和输出操作。
    * byte 是字节数据类型 ，是有符号型的，占1 个字节；大小范围为-128—127 。
      char 是字符数据类型 ，是无符号型的，占2字节(Unicode码 ）；大小范围 是0—65535 ；char是一个16位二进制的Unicode字符，JAVA用char来表示一个字符。

2. 在字节流中输出数据主要是使用 `OutputStream` 完成，输入使的是 `InputStream`，字节流一般以 IO 对象为参数构造。

3. 在字符流中输出主要是使用 `Writer` 类完成，输入流主要使用 `Reader` 类完成，字符流一般以字节流对象为参数构造。

4. `PipedInputStream` 运用的是一个数组作为数据缓冲区（默认大小为 1024 字节），并不是真正意义上的管道。写入 `PipedOutputStream` 的数据实际上保存到对应的 PipedInputStream的内部缓冲区。
   从 `PipedInputStream` 执行读操作时，读取的数据实际上来自这个内部缓冲区。如果对应的 `PipedInputStream` 输入缓冲区已满，任何企图写入 `PipedOutputStream` 的线程都将被阻塞。
   而且这个写操作线程将一直阻塞，直至出现读取 `PipedInputStream` 的操作从缓冲区删除数据。

5. 序列化并不是对类的所有的成员变量的状态都能保存
    1. 序列化对 `static` 和 `transient` 变量，是不会自动进行状态保存的。`transient` 的作用就是，用 `transient` 声明的变量，不会被自动序列化。
    2. 对于 `Socket`, `Thread` 类，不支持序列化。若实现序列化的接口中，有 `Thread` 成员；在对该类进行序列化操作时，编译会出错！
       这主要是基于资源分配方面的原因。如果 `Socket`，`Thread` 类可以被序列化，但是被反序列化之后也无法对他们进行重新的资源分配；再者，也是没有必要这样实现。

6. `FilterInputStream` 和 `FilterOutputStream` 是装饰者模式的实现，这两个 stream 接受其他的输入输出流作为参数进行封装，其继承类在封装的基础上实现更多的功能
    1.  BufferedInputStream 本质上是通过一个内部缓冲区数组实现的，在新建某输入流对应的BufferedInputStream后，当我们通过read()读取输入流的数据时，BufferedInputStream会将该输入流的数据分批的填入到缓冲区中。每当缓冲区中的数据被读完之后，输入流会再次填充数据缓冲区；如此反复，直到我们读完输入流数据位置。
        提供“缓冲功能”以及支持“mark()标记”和“reset()重置方法”。
    2. `BufferedOutputStream` 通过字节数组来缓冲数据，当缓冲区满或者用户调用flush()函数时，它就会将缓冲区的数据写入到输出流中。
    3. `DataInputStream` 和 `DataOutputStream` 允许应用程序以与机器无关方式从底层输入流中读写基本 Java 数据类型。
    4. `PrintStream` 的作用虽然也是装饰其他输出流，但是它的目的不是以与机器无关的方式从底层读写java数据类型；
       而是为其它输出流提供打印各种数据值表示形式，使其它输出流能方便的通过 `print()`, `println()` 或 `printf()` 等输出各种格式的数据。

7. `PipedWriter`、`PipedReader` 跟 `PipedInputStream`、`PipedOutputStream` 一样，都是采用数组作为中间的管道。

8. `InputStreamReader` 和 `OutputStreamWriter` 是字节流通向字符流的桥梁：它使用指定的 charset 读写字节并将其解码为字符。

9. `FileReader` 和 `FileWriter` 是专门封装的用于操作文件的字符流处理类，通过构造 `FileInputStream` 和 `FileOutputStream` 并将其封装到 `InputStreamReader` 和 `OutputStreamWriter` 中来实现。

10. `BufferedReader` 和 `BufferedWriter ` 的作用是为其他字符输入流添加一些缓冲功能

11. `RandomAccessFile` 是随机访问文件(包括读/写)的类。它支持对文件随机访问的读取和写入，即我们可以从指定的位置读取/写入文件数据。
## 类层次图

![输入流和输出流的类层次图](https://images2017.cnblogs.com/blog/663847/201801/663847-20180107231342065-2108564502.png)

## 参考

* [Java总结：Java 流(Stream)、文件(File)和IO](https://www.cnblogs.com/52fhy/p/8232825.html)

* [java io系列01之 "目录"](https://www.cnblogs.com/skywang12345/p/io_01.html)