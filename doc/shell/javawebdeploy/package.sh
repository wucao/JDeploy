#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址
# $3 finalName
# $4 contextPath
# $5 Jetty的start.jar路径
# $6 项目部署路径

rm -rf $6/$1
mkdir -p $6/$1
cd $6/$1
svn checkout $2 .
mvn clean package

java -jar $5 --add-to-startd=http,deploy
mv target/$3.war webapps/$4.war