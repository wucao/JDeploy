#!/bin/bash

# 查看日志
#
# 参数：
# $1 进程UUID
# $2 项目部署路径

tail -f -n 500 $2/$1/nohup.out