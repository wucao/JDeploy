#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址
# $3 Jetty的start.jar路径
# $4 项目部署路径
# $5 版本控制系统(1.SVN;2.GIT)
# $6 Maven profile
# $7 Git分支

if [ -z "$1" ]||[ -z "$4" ]; then
    echo "参数不能为空"
    exit 0
fi

rm -rf $4/$1
mkdir -p $4/$1
cd $4/$1

if [ $5 -eq 1 ]
then
	svn checkout $2 .
else
	git clone $2 .
	if [ "$7" != "null" ]; then
        git checkout $7
    fi
fi

if [ "$6" != "null" ]; then
    mvn clean package -Dmaven.test.skip=true -P$6
else
    mvn clean package -Dmaven.test.skip=true
fi

java -jar $3 --add-to-startd=http,deploy,jsp,websocket