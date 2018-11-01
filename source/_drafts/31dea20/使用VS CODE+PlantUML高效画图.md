---
layout: post
title:  "使用VS CODE+PlantUML高效画图"
title2:  "使用VS CODE+PlantUML高效画图"
date:   2018-11-01 02:57:46  +0800
source:  "https://blog.csdn.net/qq_15437667/article/details/70163125"
fileName:  "31dea20"
lang:  "zh_CN"
published: false

---
> 自从发现了plantuml写脚本画图的方式之后，爱上了画图~
> 
> 环境：MAC

## 前言

本文多数内容引用自官网文档和其他人的教程，并非本人原创，也谈不上翻译，只是把自己 理解的东西用中文写出来。

## 什么是PLANTUML

PlantUML是一个快速创建UML图形的组件，官网上之所以称它是一个组件，我想主要是因为多数情况下我们都是在Eclipse、NetBenas、Intellijidea、 Emacs、Word、VS CODE、Sublime等软件里来使用PlantUML（参看各软件相关配置）。

PlantUML支持的图形有：

- sequence diagram,
- use case diagram,
- class diagram,
- activity diagram (here is the new syntax),
- component diagram,
- state diagram,
- object diagram,
- wireframe graphical interface

PlantUML通过简单和直观的语言来定义图形，它可以生成PNG、SVG和二进制 图片。下面是一个简单的示例：

    @startuml  Bob -> Alice : Hello, how are you Alice -> Bob : Fine, thank you, and you?  @enduml

![这里写图片描述](https://img-blog.csdn.net/20170413230545750?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMTU0Mzc2Njc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 安装plantuml

plantuml实际上是安装的插件（VS CODE插件），需要graphviz渲染画图，以及需要安装java支撑plantuml运行

1. 首先你需要安装VS CODE，一般都有
2. 安装plantuml插件
3. 去 [graphviz官网](http://graphviz.org/)下载其安装包
4. 安装java

安装好之后，新建一个文件，使用上面的示例，拷贝之后，在win32下是alt+d，mac下是command+d即可生成相关uml图

## 将脚本导出成图片

在脚本处右键，即有导出图片的选项

## 语法

语法可以参照这篇文章，非常详细

[http://archive.3zso.com/archives/plantuml-quickstart.html#sec-5-1](http://archive.3zso.com/archives/plantuml-quickstart.html#sec-5-1)

或者想快速用可以参考这篇，比较简略

[http://www.jianshu.com/p/e92a52770832](http://www.jianshu.com/p/e92a52770832)
