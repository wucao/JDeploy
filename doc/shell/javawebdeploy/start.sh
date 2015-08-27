#!/bin/bash

# 启动程序
#
# 参数：
# $1 UUID
# $2 端口号
# $3 Jetty的start.jar路径
# $4 项目部署路径

cd $4/$1
nohup java -jar $3 jetty.port=$2 projectuuid=$1 > $4/$1/nohup.out 2>&1 &
echo "服务已启动"