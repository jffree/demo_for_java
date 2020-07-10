# IO STREAM

1. 在 `java.io` 包中操作文件内容的主要有两大类：字节流（一个字节为单位）、字符流（两个字节为单位），两类都分为输入和输出操作。

2. 在字节流中输出数据主要是使用 `OutputStream` 完成，输入使的是 `InputStream`，字节流一般以 IO 对象为参数构造。

3. 在字符流中输出主要是使用 `Writer` 类完成，输入流主要使用 `Reader` 类完成，字符流一般以字节流对象为参数构造。


## 类层次图

![输入流和输出流的类层次图](https://images2017.cnblogs.com/blog/663847/201801/663847-20180107231342065-2108564502.png)

## 参考

* [Java总结：Java 流(Stream)、文件(File)和IO](https://www.cnblogs.com/52fhy/p/8232825.html)