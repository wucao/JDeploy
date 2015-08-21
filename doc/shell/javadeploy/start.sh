#!/bin/bash

# 启动程序
#
# 参数：
# $1 UUID
# $2 finalName

echo 'nohup java -jar /coder/deploy/$1/target/$2.jar > /coder/deploy/$1/nohup.out 2>&1 &'
nohup java -jar /coder/deploy/$1/target/$2.jar > /coder/deploy/$1/nohup.out 2>&1 &