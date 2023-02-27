package com.learn.queue.list;

import java.util.Objects;

/**
 * Project: DataStructruer
 * File Created at 2022-01-16 22:11:22:11
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type Person.java
 * @desc
 * @date 2022/1/16 0016 22:11
 */
public class Person {
    private int age;
    private String name;

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
                '}';
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("销毁person了");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
