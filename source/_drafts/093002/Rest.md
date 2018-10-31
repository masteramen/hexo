---
layout: post
title: "Rest"
title2: "new post"
date: 2018-10-30 03:12:15  +0800
source: ""
fileName: "093002"
lang: "zh_CN"
published: false
---

### 什么是 REST?

REST 全称 REpresentation State Transfer（表现层状态转移）

### 什么是资源？

### 什么是安全的 Rest 操作？

Restful API 的设计就是通过 HTTP 的方法来表示 CRUD 的相关操作。除了使用 GET 和 POST 方法外，还会用到其他 HTTP 方法，比如 PUT,DELETE,HEAD 等，通过不同的 HTTP 方法来表示不同的含义。

### Spring MVC Restful

- @RestController
- @RequestMapping
- @PathVariable
- @RequestBody
- @ResponseBody
- MockMvc

### JUnit and Mockito

### Spring-Data-JPA JPA Hiberanate

Spring Data JPA 是 Spring 基于 Hiberate 开发的一个 JPA 框架。

Spring Data 接口： CrudRepository

JPQL 查询语言：通过面向对象而非面向数据库的查询语言，别买程序的 SQL 语句紧密耦合。

JPA 仅仅是一种规范，也就是说 JPA 仅仅定义了一些接口。

###什么是 Spring data jpa?
Spring data jpa 是 Spring 提供的一套简化 JPA 开发的框架，按照约定好的【方法命名规则】写 Dao 层接口，就可以在不写接口实现的情况下，实现对数据库的访问和操作。同时提供了很多除了 CRUD 之外的功能，比如分页，排序，复杂查询等等。

left join, 左连接：返回左表中的所有记录以及右表中的联接字段相等的记录。
right join， 右联接：返回右表中的所有记录以及左表的联接字段相等的记录
innert join,等值联接：只返回两个表中联接字段相等的记录。

#Hibernate

- HQL
- QBC
- Native SQL(不能夸平台)
- Transient, Persistent, Detached
