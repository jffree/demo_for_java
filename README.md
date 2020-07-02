# demo_for_java

*Some demo code based on java.*

## network model

* [Bio Model (use Thread)](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/network_model/bio_model)

* [Bio Model (use ThreadPool)](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/network_model/bio_model_with_theadpool)

* [Basic Nio Reactor Model](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/network_model/basic_reactor)

* [Nio Reactor Model With MultiHandler](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/network_model/reactor_with_multiHandler)

* [Nio Reactor Model With MultiReactor](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/network_model/reactor_with_subReactor)


* [Aio Model](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/network_model/aio_model)

* **Reference:**   
  1. *[Scalable IO in Java](http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf)*
  2. *《Netty 权威指南（第二版）》*

## Serialization And Deserialization Protocol

* [fastjson](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/protocol/fastjson)
	1. 参考地址：[Quick Start CN](https://github.com/alibaba/fastjson/wiki/Quick-Start-CN)
* [protobuf](https://github.com/jffree/demo_for_java/tree/master/src/main/java/com/jffree/java_demo/protocol/protobuf)
	1. protobuf 需要预安装 Protocol 编译器，请参考 [Protocol Buffers - Google's data interchange format](https://github.com/protocolbuffers/protobuf/blob/master/README.md)
	2. 使用说明，请参考：[Use Java Protocol Buffers](https://github.com/protocolbuffers/protobuf/tree/master/java)
	3. 语法手册，请参考：
		* [Language Guide](https://developers.google.com/protocol-buffers/docs/overview)
		* [Protobuf3 语法指南](https://colobu.com/2017/03/16/Protobuf3-language-guide/)
		* [Protobuf2 语法指南](https://colobu.com/2015/01/07/Protobuf-language-guide/)
	4. 本项目中 proto 文件存放位置为： `resources/protobuf/msg.proto`
	5. 将 proto 描述文件编译为 java 类文件：`protoc --java_out=src/main/java/ -I= src/main/resources/protobuf/msg.proto`