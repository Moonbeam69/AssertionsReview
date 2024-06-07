package org.mine.LoTR;

public class Hobbit {
  String  name;
  int age = 0;

  public Hobbit(String name) {
    this.name = name;
  }

  public Hobbit(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
