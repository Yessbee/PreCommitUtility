# PreCommitUtility
PreCommitUtility/
├── pom.xml
├── README.md
├── copilot-mcp.json
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/precommit/
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
