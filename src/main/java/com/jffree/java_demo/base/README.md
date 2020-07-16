# Base

1. `equals` 和 `==`
    1. `==`是一个比较运算符，基本数据类d型比较的是值，引用数据类型比较的是地址值。
    2. `equals()` 是一个方法，只能比较引用数据类型。重写前比较的是地址值，重写后比一般是比较对象的属性。
        1. 自反性：对于任何非空引用x，x.equals(x)应该返回true。
        2. 对称性：对于任何引用x和y，如果x.equals(y)返回true，那么y.equals(x)也应该返回true。
        3. 传递性：对于任何引用x、y和z，如果x.equals(y)返回true，y.equals(z)返回true，那么x.equals(z)也应该返回true。
        4. 一致性：如果x和y引用的对象没有发生变化，那么反复调用x.equals(y)应该返回同样的结果。
        5. 非空性：对于任意非空引用x，x.equals(null)应该返回false。
