#!/bin/bash

# 查看日志
#
# 参数：
# $1 进程UUID

tail -n 500 /coder/deploy/$1/nohup.out