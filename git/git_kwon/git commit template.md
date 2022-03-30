# Git

---

## Git commit template

- **커밋 내용이 무엇인지, 어떤 것을 의도하였는지 명확하게 보여줄 수 있도록 하는 것이 중요**

```
<type>[optional scope]: <short description>
<long description>
//<breaking changes>
//<closed issues>
```

### Type of change

- feat : A new feature / **새로운 기능 추가**
- fix : A bug fix / **버그 수정**
- docs : Documentation only changes / **문서 수정**
- style : Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc) / **코드에 의미 없는 변화 (공백, 포멧변경, 세미콜론 누락 등...)**
- refactor : A code change that neither fixes a bug nor adds a feature / **버그 수정 혹은 기능을 추가하지 않는 수정**
- pref : A code change that improves performance / **퍼포먼스를 향상시키는 수정**
- test : Adding missing tests or correcting existing tests / **누락된 테스트 추가 혹은 기존 테스트 수정**
- build : Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm) / **빌드 시스템 혹은 외부 의존관계에 영향을 미치는 변경**
- ci : Changes to our CI configuration files and scripts (example scopes: Travis Circle, BrowserStack, SauceLabs) / **CI 설정 파일, 스크립트 수정**
- chore : Other changes that don't modify src or test files / **src 혹은 테스트 파일을 변경하지 않는 수정**
- revert : Reverts a previous commit / **이전 커밋을 되돌리는 경우**


