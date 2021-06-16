#!/usr/bin/env bash

CUR_ORIGIN=$(git remote get-url origin)
echo "当前仓库：$CUR_ORIGIN"

if [[ $CUR_ORIGIN == *github.com* ]]; then
  git remote set-url origin ${CUR_ORIGIN/github.com/gitee.com}
fi

if [[ $CUR_ORIGIN == *gitee.com* ]]; then
  git remote set-url origin ${CUR_ORIGIN/gitee.com/github.com}
fi

echo "已切换为：$(git remote get-url origin)"

if [ -n "$1" ]; then
  git $1
fi