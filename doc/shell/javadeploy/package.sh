#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址
# $3 项目部署路径

rm -rf $3/$1
mkdir -p $3/$1
cd $3/$1
svn checkout $2 .
mvn clean package -Dmaven.test.skip=true