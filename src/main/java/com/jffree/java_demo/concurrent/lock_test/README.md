# 锁

## LockSupport

1. `LockSupport` 通过“许可”（permit）机制，使用 `park/unpark` 实现线程的阻塞和唤醒。这里的“许可”与线程相关联，类似二元信号量，不可叠加且一个线程只能有一个。 
    * 调用 `unpark` 就会将这个凭证置为1，多次连续调用 `unpark`，凭证仍然是1。
    * `park` 会消耗掉一个许可，若此时许可为0，则会阻塞线程。

2. 优点
    * `unpark` 和 `park` 没有调用顺序的要求，也无需获取锁。
    * 调用 `park` 的时候还可以指定 `blocker` 对象（保存在线程 `Thread` 的对象的 `parkBlocker`里面），此对象在线程阻塞时被记录，允许监视工具和诊断工具确定线程阻塞的原因。
    * `wait` 和 `notify` 都是 `Object` 中的方法,在调用这两个方法前必须先获得锁对象，但是 `park` 不需要获取某个对象的锁就可以锁住线程。
      `notify` 只能随机选择一个线程唤醒，无法唤醒指定的线程，`unpark` 却可以唤醒一个指定的线程。
    * 取代了 `Thread.suspend` 和 `Thread.resume` 可能引发的死锁（必须要保证先使用 `suspend`，再使用 `resume`，而 `park` 和 `unpark` 则不需要保证顺序）（这两个方法已经被废弃）   

3. 底层原理
    * 每个线程都有 `Parker` 实例，`Parker` 对象中有一个整形的 `_counter` 变量，`park` 和 `unpark` 就是围绕着这个变量进行操作的。
    
4. Ref:
    * [【细谈Java并发】谈谈LockSupport](https://www.jianshu.com/p/1f16b838ccd8)

## ReentrantLock

1. `ReentrantLock` 为可重入锁，同时提供公平锁和非公平锁两种形式。 与 `Condition` 实现更灵活的控制。