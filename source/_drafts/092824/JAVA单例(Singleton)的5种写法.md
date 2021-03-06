---
layout: post
title: "JAVA面试单例(Singleton)的5种写法及优缺点"
title2: "JAVA面试单例(Singleton)5种写法的优缺点"
date: 2018-10-28 12:02:23  +0800
source: ""
fileName: "092824"
lang: "zh_CN"
published: true
---

### 1. 只适合单线程环境（不好）

```java
  package singleton;

  public class Singleton {
  private static Singleton instance=null;
  private Singleton(){
  }
  public static Singleton getInstance(){
  if(instance==null){
  instance=new Singleton();
  }
  return instance;
  }
  }
```

注解:Singleton 的静态属性 instance 中，只有 instance 为 null 的时候才创建一个实例，构造函数私有，确保每次都只创建一个，避免重复创建。
缺点：只在单线程的情况下正常运行，在多线程的情况下，就会出问题。例如：当两个线程同时运行到判断 instance 是否为空的 if 语句，并且 instance 确实没有创建好时，那么两个线程都会创建一个实例。

### 2. 多线程的情况可以用。（懒汉式，不好）

```java
  public class Singleton {
  private static Singleton instance=null;
  private Singleton(){

  }
  public static synchronized Singleton getInstance(){
  if(instance==null){
  instance=new Singleton();
  }
  return instance;
  }
  }
```

注解：在解法一的基础上加上了同步锁，使得在多线程的情况下可以用。例如：当两个线程同时想创建实例，由于在一个时刻只有一个线程能得到同步锁，当第一个线程加上锁以后，第二个线程只能等待。第一个线程发现实例没有创建，创建之。第一个线程释放同步锁，第二个线程才可以加上同步锁，执行下面的代码。由于第一个线程已经创建了实例，所以第二个线程不需要创建实例。保证在多线程的环境下也只有一个实例。
缺点：每次通过 getInstance 方法得到 singleton 实例的时候都有一个试图去获取同步锁的过程。而众所周知，加锁是很耗时的。能避免则避免。

### 3.加同步锁时，前后两次判断实例是否存在（可行）

```java
  public class Singleton {
  private static Singleton instance=null;
  private Singleton(){ }
  public static Singleton getInstance(){
  if(instance==null){
  synchronized(Singleton.class){
  if(instance==null){
  instance=new Singleton();
  }
  }
  }
  return instance;
  }
}
```

注解：只有当 instance 为 null 时，需要获取同步锁，创建一次实例。当实例被创建，则无需试图加锁。
缺点：用双重 if 判断，复杂，容易出错。

### 4.饿汉式（建议使用）

```java
  public class Singleton {
  private static Singleton instance=new Singleton();
  private Singleton(){
  }
  public static Singleton getInstance(){
  return instance;
  }
  }
```

注解：初试化静态的 instance 创建一次。如果我们在 Singleton 类里面写一个静态的方法不需要创建实例，它仍然会早早的创建一次实例。而降低内存的使用率。

缺点：没有 lazy loading 的效果，从而降低内存的使用率。

### 5.静态内部内。（建议使用）

```java
  public class Singleton {
  private Singleton(){

  }
  private static class SingletonHolder{
  private final static Singleton instance=new Singleton();
  }
  public static Singleton getInstance(){
  return SingletonHolder.instance;
  }
  }
```

注解：定义一个私有的内部类，在第一次用这个嵌套类时，会创建一个实例。而类型为 SingletonHolder 的类，只有在 Singleton.getInstance()中调用，由于私有的属性，他人无法使用 SingleHolder，不调用 Singleton.getInstance()就不会创建实例。
优点：达到了 lazy loading 的效果，即按需创建实例。
