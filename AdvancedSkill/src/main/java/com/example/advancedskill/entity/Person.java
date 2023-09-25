package com.example.advancedskill.entity;

import java.io.Serializable;

/*这里我们让Person类实现了Serializable接口，这样所有的Person对象都是可序列化的了*/
public class Person implements Serializable {

   private String name;
   private int age;

   @Override
   public String toString() {
      return "Person{" +
              "name='" + name + '\'' +
              ", age=" + age +
              '}';
   }

   public Person(String name, int age) {
      this.name = name;
      this.age = age;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }
}
