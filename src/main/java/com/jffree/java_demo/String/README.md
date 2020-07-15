# String 相关

1. 类图：
![继承图](https://images0.cnblogs.com/blog/497634/201311/08083111-591e2833f8a34264b0dad417f4188e35.jpg)

2. 区别
    1. `String` 类是不可变类，即一旦一个 `String` 对象被创建以后，包含在这个对象中的字符序列是不可改变的，直至这个对象被销毁。
    2. `StringBuffer` 对象则代表一个字符序列可变的字符串。
       当一个 `StringBuffer` 被创建以后，通过 `StringBuffer` 提供的 `append()、insert()、reverse()、setCharAt()、setLength()` 等方法可以改变这个字符串对象的字符序列。
       一旦通过 `StringBuffer` 生成了最终想要的字符串，就可以调用它的 `toString()` 方法将其转换为一个 `String` 对象。
    3. `StringBuilder` 类也代表可变字符串对象。实际上，`StringBuilder` 和 `StringBuffer` 基本相似，两个类的构造器和方法也基本相同。
       不同的是：`StringBuffer` 是线程安全的，而 `StringBuilder` 则没有实现线程安全功能，所以性能略高。
       
4. 使用
    1. 如果要操作少量的数据用 `String`
    2. 单线程操作字符串缓冲区 下操作大量数据使用 `StringBuilder`
    3. 多线程操作字符串缓冲区 下操作大量数据使用 `StringBuffer`
    
5. 参考：

[String、StringBuffer和StringBuilder区别](https://www.cnblogs.com/AmyZheng/p/9415064.html)

[String详解, String和CharSequence区别, StringBuilder和StringBuffer的区别 (String系列之1)](https://www.cnblogs.com/skywang12345/p/string01.html)

[StringBuilder 详解 (String系列之2)](https://www.cnblogs.com/skywang12345/p/string02.html)

[StringBuffer 详解 (String系列之3)](https://www.cnblogs.com/skywang12345/p/string03.html)