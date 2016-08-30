#!/bin/bash

# SVN检出并Maven打包
#
# 参数：
# $1 UUID
# $2 SVN地址
# $3 项目部署路径
# $4 版本控制系统(1.SVN;2.GIT)
# $5 Maven profile
# $6 Git分支

if [ -z "$1" ]||[ -z "$3" ]; then
    echo "参数不能为空"
    exit 0
fi

rm -rf $3/$1
mkdir -p $3/$1
cd $3/$1

if [ $4 -eq 1 ]
then
	svn checkout $2 .
else
	git clone $2 .
	if [ "$6" != "null" ]; then
	    git checkout $6
	fi
fi

if [ "$5" != "null" ]; then
    mvn clean package -Dmaven.test.skip=true -P$5
else
    mvn clean package -Dmaven.test.skip=true
fi