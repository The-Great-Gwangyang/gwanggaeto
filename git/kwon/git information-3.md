# Git

---

## Git에 대해서

### .gitignore

- Git 버전 관리에서 제외할 파일 목록을 지정하는 파일.

```zsh
# 이렇게 #를 사용해서 주석

# 모든 file.c
file.c

# 최상위 폴더의 file.c
/file.c

# 모든 .c 확장자 파일
*.c

# .c 확장자지만 무시하지 않을 파일
!not_ignore_this.c

# logs란 이름의 파일 또는 폴더와 그 내용들
logs

# logs란 이름의 폴더와 그 내용들
logs/

# logs 폴더 바로 안의 debug.log와 .c 파일들
logs/debug.log
logs/*.c

# logs 폴더 바로 안, 또는 그 안의 다른 폴더(들) 안의 debug.log
logs/**/debug.log
```

#### 알아두면 좋은 사이트

 - [.gitignore 제작 사이트](https://www.toptal.com/developers/gitignore)
 - [github gitignore관련 저장소](https://github.com/github/gitignore)

