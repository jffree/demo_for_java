# 时间处理

## 旧版 API，java 8 之前

* 这些工具都存在与 `java.util` 包中。*

1. `TimeZone`：表示时区偏移量，也可以计算夏令时。

2. `Locale` 表示地区。每一个Locale对象都代表了一个特定的地理、政治和文化地区。也代表了这个地区的时间表示方式。

2. `Date`：一个 `Date` 对象表示一个特定的瞬间，能精确到毫秒。但是 `Date` 没有时区的概念。不要使用 `Data` 操作“年月日时分秒”，而应该使用 `Calendar` 或者 `DateFormat`

3 `Calendar` 对日期时间的操作更为友好，对国际化的支持也更为友好，同时也支持时间的加减操作。
   * 它的实现，采用了设计模式中的工厂方法。
   * 注意， Calendar 为了提高计算的效率，使用了缓存的机制，
     也就是计算过时间（getTimeInMillis、getTime）后就会将时间缓存下来，下次计算的时候就不再进行计算。
     使用 Calendar 的正确方式应该是：
        1. 实例化 Calendar
        2. 重复利用 Calendar 实例：
            set（设置时间） -> setTimeZone（设置时区） -> getTime（获得时间） -> clear（清除时间）
                 |_________________________________________________________________|
        
4. `DateFormat` 的作用是 格式化并解析“日期/时间”。功能比较少，其子类 `SimpleDateFormat` 是常用的类。

## 新版 API

* 在 `java.time` 包中提供，线程安全（对象不可变）*

* 新API修正了旧API不合理的常量设计：
  1. Month的范围用1~12表示1月到12月；
  2. Week的范围用1~7表示周一到周日。
  
* 本地日期和时间：`LocalDateTime`，`LocalDate`，`LocalTime`，严格按照ISO 8601规定的日期和时间格式进行打印和解析

* 带时区的日期和时间：`ZonedDateTime`

* 时刻：`Instant`，代表当前的时间戳

* 时区：`ZoneId`，`ZoneOffset`

* 时间间隔：`Duration`

## 参考

1. [时间与日期](https://www.liaoxuefeng.com/wiki/1252599548343744/1303871087444002)

2. [Java Calendar,Date,DateFormat,TimeZone,Locale等时间相关内容的认知和使用](https://www.cnblogs.com/skywang12345/p/3327482.html)
