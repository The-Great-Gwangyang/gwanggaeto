# MongoDB

## MongoDB 특징
 - Schema가 자유롭다.
 - HA와 Scale-Out Solution을 자체적으로 지원해서 확장이 쉽다.
 - Secondary Index를 지원하는 NoSQL이다.
 - 다양한 종류의 Index를 제공한다.
 - 응답 속도가 빠르다.
 - 배우기 쉽고 간편하게 개발이 가능하다.

## MongoDB 구조

### RDBMS & MongoDB
 - Cluster -> Cluster
 - Database -> Database
 - Table -> Collection
 - Row -> Document
 - Column -> Field

### 기본 Database
 - admin
   - 인증과 권한 부여 역할
   - 일부 관리 작업을 하려면 admin Database에 대한 접근이 필요
 - local
   - 모든 Mongodb instance는 local database를 소유한다.
   - oplog와 같은 replication 절차에 필요한 정보를 저장한다.
   - startup_log와 같은 instance 진단 정보를 저장한다.
   - local database 자체는 복제되지 않는다.
 - config
   - sharded cluster에서 각 shard의 정보를 저장한다.

### Collection 특징
 - 동적 스키마를 갖고 있어서 스키마를 수정하려면 필드 값을 추가/수정/삭제하면 된다.
 - Collection 단위로 Index를 생성할 수 있다.
 - Collection 단위로 Shard를 나눌 수 있다.

### Document 특징
 - JSON 형태로 표현하고 BSON(Binary JSON) 형태로 저장한다.
 - 모든 Document에는 "_id"필드가 있고, 없이 생성하면 OBjectId 타입의 고유한 값을 저장한다.
 - 생성 시, 상위 구조인 Database나 Collection이 없다면 먼저 생성하고 Document를 생성한다.
 - Document의 최대 크기는 **16MB**이다.

### 정리...
 - Database -> Collection -> Document -> Field 순으로 구조가 형성되어 있다.
 - admin, config, local database는 MongDB를 관리하는데 사용된다.
 - Collection은 동적 스키마를 갖는다.
 - Document는 JSON 형태로 표현되고 BSON 형태로 저장된다.
 - Document는 고유한 "_id" 필드를 항상 갖고 있다.
 - Document의 최대 크기는 **16MB**로 고정되어 있다.




















