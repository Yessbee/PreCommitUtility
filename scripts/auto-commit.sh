#!/bin/bash
REPO_ROOT=$(git rev-parse --show-toplevel)
JAR="$REPO_ROOT/PreCommitUtility/target/PreCommitUtility-1.0.0.jar"

msg=$(java -cp "$JAR" com.example.precommit.PreCommitCli "$@")

if [[ $msg == ERROR:* ]]; then
  echo "$msg"
  exit 1
fi

echo "Commit message:"
echo "$msg"
echo
read -p "Press ENTER to commit or Ctrl+C to cancel..."

git commit -m "$msg"
