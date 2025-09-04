@echo off
for /f "tokens=*" %%i in ('git rev-parse --show-toplevel') do set REPO_ROOT=%%i
set HOOK_DIR=%REPO_ROOT%\.git\hooks

if not exist "%HOOK_DIR%" mkdir "%HOOK_DIR%"
copy "%~dp0git-hooks\prepare-commit-msg.bat" "%HOOK_DIR%\prepare-commit-msg" >nul

echo ✅ Git hook installed at %HOOK_DIR%\prepare-commit-msg