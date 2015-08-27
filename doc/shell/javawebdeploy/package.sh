#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址
# $3 finalName
# $4 contextPath

rm -rf /coder/deploy/$1
mkdir -p /coder/deploy/$1
cd /coder/deploy/$1
svn checkout $2 .
mvn clean package

cd /coder/deploy/$1
java -jar /coder/jetty/jetty-distribution-9.2.7.v20150116/start.jar --add-to-startd=http,deploy
mv target/$3.war webapps/$4.war