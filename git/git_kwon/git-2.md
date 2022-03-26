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

```
git clone
```

- init : 빈 Git repository를 만들거나 기존 Git 저장소를 다시 초기화 / .git 파일 생성

```
git init
```

#### 2. 변경 작업

- add : 인덱스에 파일을 추가

```
git add [파일/디렉토리 경로]

# 현재 디렉토리(디렉토리 이하...)의 모든 변경 내용을 staging area로 넘길 때
git add .

# 작업 디렉토리 내의 모든 변경 내용을 모두 staging area로 넘길 때
git add -A

# 터미널에서 각 변경사항을 직접 하나씩 확인하여 staging area로 넘길 때
git add -p
```

- mv : 파일, 디렉토리를 이동하거나 이름을 변경

```
git mv [원본 이름] [변경할 이름]
```

- restore : workspace 파일을 복원

```
# 특정 파일 HEAD Commit으로 복구
git restore [파일 이름]

# 특정 파일 특정 commit으로 복구
git restore --source [commit hash] [파일 이름]

# staging area에 올라간 파일 다시 Unstaging 시키기
git restore --staged [파일 이름]
```

- rm : 인덱스 또는 workspace 파일을 삭제

```
# 원격 저장소, 로컬 저장소에 있는 파일 삭제
git rm [파일 이름]

# 원격 저장소 파일 삭제(로컬 저장소 파일은 삭제하지 않는다)
git rm --cached [파일 이름]
```

- sparse-checkout : 저장소의 일부분만 가져옴

```
git sparse-checkout init
git sparse-checkout set "/scripts/"
# sparse-checkout 확인
git sparse-checkout list
# 기존과 같이 pull을 하면 scripts 폴더만 다운로드 된다.
```

#### 3. 이력 및 상태 확인

- bisect : 이진 검색을 사용하여 버그 발생시킨 커밋 찾기
- diff : 커밋들과 커밋과 workspace간의 차이를 확인
- grep :
- log : 커밋 로그 표시
- show :
- status : stagingi area 상태를 표시

---

