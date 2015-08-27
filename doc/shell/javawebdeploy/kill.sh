#!/bin/bash
# kill进程
#
# 参数：
# $1 UUID

pid=$(ps -ef | grep $1 | grep -v grep | grep -v kill.sh | cut -c 9-15)
if [ -n "$pid" ]; then
    kill $pid
    echo "服务已停止"
fi