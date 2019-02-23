---
layout: post
title: "ssh使用指定远程目录执行命令"
date: 2019-02-23 06:39:03  +0800
source: ""
fileName: "012324"
lang: "zh_CN"
published: false
---

ssh 使用指定  远程目录执行目录可以使用 -t 选项  cd 到指定目录， 然后执行相应的命令：

```bash
    ssh user@server -t "cd /directory ; /bin/bash"
```
