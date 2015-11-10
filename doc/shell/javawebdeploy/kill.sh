#!/bin/bash
# kill进程
#
# 参数：
# $1 UUID

pkill -f $1
echo "服务已停止"