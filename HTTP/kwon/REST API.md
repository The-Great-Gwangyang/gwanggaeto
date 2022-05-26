# REST API(RESTful API)

---

## REST란?

- REpresentational State Transfer : 자원을 이름으로 구분해, 해당 자원의 상태를 주고 받는 모든 것. 즉, 자원(resource)의 표현(representation)에 의한 상태 전달.

## REST의 구성요소

1. 자원(Resource) : URI

> 모든 자원에는 고유한 ID가 존재하고, 이 자원은 Server에 존재
> 자원을 구별하는 ID는 HTTP URI
> Client는 URI를 이용해 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청

2. 행위(Verb) : HTTP Method

| Method | 특징 |
| ------ | -------- |
| GET  | Read : 정보 요청, URI가 가진 정보를 검색하기 위해 서버에 요청 |
| POST | Create : 정보 입력, 클라이언트에서 서버로 전달하려는 정보를 보냄 |
| PUT  | Update : 정보 업데이트, 주로 내용을 갱신하기 위해 사용(전체) |
| PATCH  | Update : 정보 업데이트, 주로 내용을 갱신하기 위해 사용(일부) |
| DELETE  | Delete : 정보 삭제(안전성 문제로 대부분 서버에서 비활성화 |

3. 표현(Representations)

> Client와 Server가 데이터를 주고 받는 형태로는 JSON, XML, TEXT, RSS 등이 있음  
> JSON, XML을 통해 데이터를 주고 받는 것이 일반적

## REST의 특징

### 1. Server-Client (서버-클라이언트 구조)
- Rest 서버는 API 제공을 하고 클라이언트는 사용자 인증에 관련된 일들을 직접 관리
- 자원이 있는 쪽을 Server라고 하고 자원을 요청하는 쪽이 Client
- 서로간의 의존성이 줄어들기 때문에 역할이 확실하게 구분되어 개발해야할 내용들이 명확

### 2. Stateless (무상태)
- Rest는 상태 정보를 유지하지 않음
- 서버는 각각의 요청을 완전히 다른 것으로 인식하고 처리
- 이전 요청이 다음 요청 처리에 연관이 없음

### 3. Cacheable (캐시 처리 기능)
- HTTP의 기존 웹 표준을 그대로 사용하기 때문에 HTTP가 가진 캐싱 기능 적용이 가능

### 4. Layered System (계층 구조)
- Client는 REST API Server만 호출
- REST Server는 다중 계층으로 구성될 수 있음

### 5. Uniform Interface (인터페이스 일관성)
- URI로 지정한 Resource에 대한 요청을 통일되고, 한정적으로 수행하는 아키텍처 스타일을 의미
- 특정 언어나 기술에 종속되지 않음

### 6. Self-Descriptiveness (자체 표현)
- 요청 메시지만 보고도 쉽게 이해할 수 있는 자체 표현 구조로 되어 있음

## REST API란?

- REST의 특징을 기반으로 서비스 API를 구현한 것
- RESTful API > REST의 규칙을 굉장히 잘 지킨 것

### REST API 설계 규칙

#### 1. URI는 명사를 사용한다.(리소스명은 동사가 아닌 명사를 사용해야 한다.)
```
<Bad Case>
/getAllUsers
/getUserById
/updateUser

<Good Case>
/Users
/User/Id
/User
```
#### 2. 슬래시( / )로 계층 관계를 표현

#### 3. URI 마지막 문자로 슬래시 ( / )를 사용하지 않음

#### 4. 밑줄( _ )을 사용하지 않고, 하이픈( - )을 사용

#### 5. URI는 소문자로만 구성

#### 6. HTTP 응답 상태 코드 사용

#### 7. 파일확장자는 URI에 미포함
- ex) http://dev-coco.tistory.com/restapi/220/photo.jpg (X)