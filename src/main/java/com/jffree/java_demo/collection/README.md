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
    
## Map 相关

1. 继承关系图
    ![](https://images0.cnblogs.com/blog/497634/201309/08221402-aa63b46891d0466a87e54411cd920237.jpg)
    
2. 综述
    1. Map 是映射接口，Map中存储的内容是键值对(key-value)。
    2. AbstractMap 是继承于Map的抽象类，它实现了Map中的大部分API。其它Map的实现类可以通过继承AbstractMap来减少重复编码。
    3. SortedMap 是继承于Map的接口。SortedMap中的内容是排序的键值对，排序的方法是通过比较器(Comparator)。
    4. NavigableMap 是继承于SortedMap的接口。相比于SortedMap，NavigableMap有一系列的导航方法；如"获取大于/等于某对象的键值对"、“获取小于/等于某对象的键值对”等等。
    5. TreeMap 继承于AbstractMap，且实现了NavigableMap接口；因此，TreeMap中的内容是“有序的键值对”！
        * 通过红黑树实现的
    6. HashMap 继承于AbstractMap，但没实现NavigableMap接口；因此，HashMap的内容是“键值对，但不保证次序”！
        * HashMap的key、value都可以为null
    7. Hashtable 虽然不是继承于AbstractMap，但它继承于Dictionary(Dictionary也是键值对的接口)，而且也实现Map接口；因此，Hashtable的内容也是“键值对，也不保证次序”。但和HashMap相比，Hashtable是线程安全的，而且它支持通过Enumeration去遍历。
        * `Dictionary` 类是一个已经被废弃的类（见其源码中的注释），所以 `Hashtable` 也相当于废弃。
        * Hashtable的key、value都不可以为null。
    8. WeakHashMap 继承于AbstractMap。它和HashMap的键类型不同，WeakHashMap的键是“弱键”。
        * HashMap实现了Cloneable和Serializable接口，而WeakHashMap没有。
        
## Set 相关

1. 继承关系图
        ![](https://images0.cnblogs.com/blog/497634/201309/09223827-04741ce6b3f84b3ab76cee8dd316b403.jpg)
2. 综述：
    1. Set 是继承于Collection的接口。它是一个不允许有重复元素的集合。
    2. AbstractSet 是一个抽象类，它继承于AbstractCollection，AbstractCollection实现了Set中的绝大部分函数，为Set的实现类提供了便利。
    3. HastSet 和 TreeSet 是Set的两个实现类。
            HashSet依赖于HashMap，它实际上是通过HashMap实现的。HashSet中的元素是无序的。
            TreeSet依赖于TreeMap，它实际上是通过TreeMap实现的。TreeSet中的元素是有序的。
    
##  Iterator 与 Enumeration

1. Enumeration，我们只能读取集合的数据，而不能对数据进行修改，Iterator除了能读取集合的数据之外，也能数据进行删除操作。

2. Iterator支持fail-fast机制，而Enumeration不支持。
