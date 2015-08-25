#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址

rm -rf /coder/deploy/$1
mkdir -p /coder/deploy/$1
cd /coder/deploy/$1
svn checkout $2 .
mvn clean package