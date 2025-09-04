# PreCommitUtility
PreCommitUtility/
├── pom.xml
├── README.md
├── copilot-mcp.json
├── src/
│   └── main/
│       ├── java/
│       │   └── com/drona/precommit/
│       │       ├── PreCommitApplication.java
│       │       ├── PreCommitCli.java
│       │       ├── CommitMessageTool.java
│       │       ├── ToolConfig.java
│       │       └── GitUtils.java
│       └── resources/
│           └── application.yml
└── scripts/
    ├── auto-commit.sh
    ├── auto-commit.bat
    ├── install-hook.sh
    ├── install-hook.bat
    └── git-hooks/
        ├── prepare-commit-msg
        └── prepare-commit-msg.bat

# PreCommitUtility

A dual-mode tool that works as:
- **MCP Server** for GitHub Copilot (stdio transport) to expose commit tools.
- **Standalone CLI** (via `.bat`/`.sh`) to auto-generate commit messages or accept a custom summary.

## Features
- Extracts Jira ID from current git branch (e.g., `ROP-2843` from `ROP-2843-refactor-models`).
- Generates commit message from staged changes **or** a custom summary.
- Git hook installer to enforce commit message format on every `git commit`.
- Portable scripts that auto-resolve the project path.

## Build
```bash
mvn clean package
