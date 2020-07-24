package com.jffree.java_demo.base;

import java.util.Date;
import java.util.Objects;

public class EqualsDemo {

    public static  class Employee {

        private String name;
        private double salary;
        private Date hireDay;

        public Employee(String name, double salary, Date hireDay) {
            this.name = name;
            this.salary = salary;
            this.hireDay = hireDay;
        }

        @Override
        public boolean equals(Object obj) {
            // 如果为同一对象的不同引用,则相同
            if (this == obj) {
                return true;
            }
            // 如果传入的对象为空,则返回false
            if (obj == null) {
                return false;
            }

            // 如果两者属于不同的类型,不能相等
            if (getClass() != obj.getClass()) {
                return false;
            }

            // 类型相同, 比较内容是否相同
            Employee other = (Employee) obj;

            return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
        }
    }

    public static void main(String[] args) {
        Date current = new Date();
        Employee e1 = new Employee("xiaowang", 12, current);
        Employee e2 = new Employee("xiaowang", 12, current);
        Employee e3 = new Employee("xiaoli", 12, current);
        Employee e4 = new Employee("xiaoli", 13, current);
        assert e1.equals(e2);
        assert !e1.equals(e2);
        assert !e2.equals(e3);
        assert !e3.equals(e4);
    }

}

