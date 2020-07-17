# 集合相关

## List 相关

1. `ArrayList` 是一个数组队列，相当于动态数组。

2. fail-fast 机制是java集合(`Collection`)中的一种错误机制。
   例如：当某一个线程A通过iterator去遍历某集合的过程中，若该集合的内容被其他线程所改变了；那么线程A访问集合时，就会抛出ConcurrentModificationException异常，产生fail-fast事件。
   原理：
   1. 集合中使用 `modCount` 来记录改变ArrayList元素的个数的操作。
   1. 创建迭代器时，会使用变量 `expectedModCount` 记录当时集合中的 `modCount`，并且不会保持同步。
   2. 使用迭代器的 `next()` 和 `remove()` 方法时，都会区检查 `expectedModCount` 与集合中的 `modCount` 是否一样，若不一样则抛出异常。

3. `LinkedList` 是一个继承于 `AbstractSequentialList` 的双向链表。它也可以被当作堆栈、队列或双端队列进行操作。
   `LinkedList` 实现 `List` 接口，能对它进行队列操作。
   `LinkedList` 实现 `Deque` 接口，即能将 `LinkedList` 当作双端队列使用
        1. LinkedList可以作为FIFO(先进先出)的队列
        2. LinkedList可以作为LIFO(后进先出)的栈
        3. 顺序访问速度很快，随机访问速度很慢
        
4. `Vector` 的原理与 `ArrayList` 是一样的，但 `Vector` 中的操作是线程安全的。
   Vector没有实现序列化接口。

5. `Stack` 很简单，它继承于 `Vector`。 `Stack` 是栈。它的特性是：先进后出(`FILO, First In Last Out`)。

6. 使用总结
    1. 对于需要快速插入，删除元素，应该使用LinkedList。
    2. 对于需要快速随机访问元素，应该使用ArrayList。
    3. 对于“单线程环境” 或者 “多线程环境，但List仅仅只会被单个线程操作”，此时应该使用非同步的类。