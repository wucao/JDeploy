#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址

echo "rm -rf /coder/deploy/$1"
rm -rf /coder/deploy/$1

echo "mkdir -p /coder/deploy/$1"
mkdir -p /coder/deploy/$1

echo "cd /coder/deploy/$1"
cd /coder/deploy/$1

echo "svn checkout $2 ."
svn checkout $2 .

echo "mvn clean package"
mvn clean package