package com.mk.mongodb.bean;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Person {

  @Id
  private String id;
  private String name;
  private String address;
  //生日
  private Date birthday;
  //财富
  private Double money;
  private Integer deptno;

  public Person() {
  }

  public Person(String id, String name, String address, Date birthday, Double money, Integer deptno) {

    this.id = id;
    this.name = name;
    this.address = address;
    this.birthday = birthday;
    this.money = money;
    this.deptno = deptno;
  }

  @Override
  public String toString() {
    return "Person{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", birthday=" + birthday +
            ", money=" + money +
            ", deptno=" + deptno +
            '}';
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public Integer getDeptno() {
    return deptno;
  }

  public void setDeptno(Integer deptno) {
    this.deptno = deptno;
  }
}