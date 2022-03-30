# Git

---

## Git에 대해서

### Git (형상 관리 도구)

#### 1. Git 웹 호스팅 시스템

- 코드를 저장할 서버
- ex) GitHub, GitLab, BitBucket

#### 2. Git GUI

- 많은 명령어로 CLI 작업하기 어려울 경우 GUI를 통해 쉽게 적용 가능
- ex) GitHub Desktop, SourceTree, GitKraken...

### Git 프로세스

![](https://blog.kakaocdn.net/dn/b6yKGG/btrbhYyXseX/jNc62M0xDmeclOzwY6c0K0/img.png)

- workspace : working directory / working tree
- index : staging area
- local repository : repository / .git directory

### Git 용어

#### 1. 작업 시작

- clone : 새로운 디렉토리에 repository 복제

```zsh
git clone
```

- init : 빈 Git repository를 만들거나 기존 Git 저장소를 다시 초기화 / .git 파일 생성

```zsh
git init
```

#### 2. 변경 작업

- add : 인덱스에 파일을 추가

```zsh
git add [파일/디렉토리 경로]

# 현재 디렉토리(디렉토리 이하...)의 모든 변경 내용을 staging area로 넘길 때
git add .

# 작업 디렉토리 내의 모든 변경 내용을 모두 staging area로 넘길 때
git add -A

# 터미널에서 각 변경사항을 직접 하나씩 확인하여 staging area로 넘길 때
git add -p
```

- mv : 파일, 디렉토리를 이동하거나 이름을 변경

```zsh
git mv [원본 이름] [변경할 이름]
```

- restore : workspace 파일을 복원

```zsh
# 특정 파일 HEAD Commit으로 복구
git restore [파일 이름]

# 특정 파일 특정 commit으로 복구
git restore --source [commit hash] [파일 이름]

# staging area에 올라간 파일 다시 Unstaging 시키기
git restore --staged [파일 이름]
```

- rm : 인덱스 또는 workspace 파일을 삭제

```zsh
# 원격 저장소, 로컬 저장소에 있는 파일 삭제
git rm [파일 이름]

# 원격 저장소 파일 삭제(로컬 저장소 파일은 삭제하지 않는다)
git rm --cached [파일 이름]
```

- sparse-checkout : 저장소의 일부분만 가져옴

```zsh
git sparse-checkout init
git sparse-checkout set "/scripts/"
# sparse-checkout 확인
git sparse-checkout list
# 기존과 같이 pull을 하면 scripts 폴더만 다운로드 된다.
```

#### 3. 이력 및 상태 확인

- bisect : 이진 검색을 사용하여 버그 발생시킨 커밋 찾기

```zsh
# 이진 탐색 시작
git bisect

# 오류발생 지점 표시
git bisect bad

# 의심 지점으로 이동
git checkout [해당 커밋 해시]

# 오류 발생 않았을 시 양호 표시
git bisect good

# 원인을 찾을 때까지 반복
# git bisect good/bad

# 이진 탐색 종료
git bisect reset
```

- diff : 커밋들과 커밋과 workspace간의 차이를 확인

```zsh
# 워킹 디렉토리의 변경사항 확인
git diff

# 파일명 확인
git diff --name-only

# 스테이지 확인
git diff --staged

# 커밋 간의 차이 확인
git diff [커밋1] [커밋2]

# 브랜치간의 차이 확인
git diff [브랜치1] [브랜치2]
```

- grep : 워킹 디렉토리 안에 내용을 문자열, 정규표현식으로 찾기

```zsh
# 워킹 디렉토리 안에 찾을 대상의 라인 번호 추가
git grep --line-number [찾을 대상]

# 워킹 디렉토리 안에 찾을 대상의 파일별로 몇 개 있는지 확인
git grep --cout [찾을 대상]

# 워킹 디렉토리 안에 찾을 대상의 매칭되는 라인이 있는 함수나 메소드 확인
git grep --show-function
```

- log : 커밋 로그 표시

```zsh
# 각 커밋마다의 변경사항 함께 확인
git log -p

# 최근 n개의 커밋만 보기
git log -[갯수]

# 통계와 함께 확인
git log --stat
git log --shortstat

# 한 줄로 보기
git log --oneline

# 변경사항 내 단어 검색
git log -S [찾을 대상]

# 커밋 메세지로 검색
git log --grep [찾을 대상]

# 그래프 로그 확인
git log --all --decorate --oneline --graph
```

- show : commit관련 다양한 정보 확인

```zsh
# 가장 최신 commit 정보 확인
git show

# 특정 시점의 commit 정보 확인
git show [해당 커밋 해시]

# 가장 최신 바로 이전의 commit 정보 확인
git show HEAD^
git show HEAD~1
```

- status : stagingi area 상태를 표시

```zsh
# 워킹 디렉토리의 상태 확인
git status
```

---

