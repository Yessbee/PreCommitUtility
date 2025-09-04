@echo off
for /f "tokens=*" %%i in ('git rev-parse --show-toplevel') do set REPO_ROOT=%%i
set JAR=%REPO_ROOT%\PreCommitUtility\target\PreCommitUtility-1.0.0.jar

for /f "tokens=* usebackq" %%a in (`java -cp "%JAR%" com.example.precommit.PreCommitCli`) do set msg=%%a

echo %msg% > %1
