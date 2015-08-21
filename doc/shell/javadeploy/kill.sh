#!/bin/bash

# kill进程
#
# 参数：
# $1 UUID

echo 'ps -ef | grep '$1' | grep -v grep | cut -c 9-15 | xargs kill'
ps -ef | grep $1 | grep -v grep | cut -c 9-15 | xargs kill