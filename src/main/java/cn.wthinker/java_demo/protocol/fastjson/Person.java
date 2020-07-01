package cn.wthinker.java_demo.protocol.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.LocalDateTime;
import java.util.Objects;

public class Person {

    @JSONField(name = "AGE", ordinal = 1)
    private int  age;

    @JSONField(name = "FULL NAME", ordinal = 0)
    private String name;

    @JSONField(name = "DATE OF BIRTH", ordinal = 3, format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;

    public LocalDateTime  getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime  birthDate) {
        this.birthDate = birthDate;
    }

    public Person(int age, String name, LocalDateTime  birthDate) {
        this.age = age;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, birthDate);
    }
}
