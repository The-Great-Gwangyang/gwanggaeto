# Spring

---

## Spring?
 - Java 기반의 웹 어플리케이션 개발을 위해 지원을 해주는 오픈소스 어플리케이션 프레임워크
 - 자바 객체와 라이브러리들을 관리해주며, 톰캣과 같은 WAS가 내장되어 있어 자바 웹 어플리케이션을 구동할 수 있다.

## Spring Version & Features

### Spring Version Release

| 버전 | 날짜   |
| ------ | -------- |
| 0.9  | 2003년 |
| 1.0  | 2004년 |
| 2.0  | 2006년 |
| 3.0  | 2009년 |
| 4.0  | 2013년 |
| 5.0  | 2017년 |

### Spring Version

#### Spring 3.0
 - Java5의 특징인 Generic, 가변인자(varargs) 등과 같은 개선사항이 추가
 - Rest API 에 대한 지원 추가

#### Spring 4.0
 - Java8의 특징들을 지원
 - 람다식, Optional, Callback Interface 등의 기능 추가
 - JPA 2.0 과 Servlet 3.0 에 대한 지원 추가
 - Web 을 개발하기 위한 도구(@RestController)들 추가
 - Web Socket 이나 STOMP 등의 프로토콜 지원
 - 테스트 환경이 개선, Framework 레벨에서 Mock을 위한 ServletContext 를 별도로 지원

#### Spring 5.0
 - Java8을 표준으로 사용
 - 코어 로직에 JDK8의 특징들이 강화
 - HTTP 메시지 코덱의 XML과 JSON 지원에 대한 구현이 Encoder 와 Decoder의 사용을 통해 추상화

## Spring Boot?
 - Spring을 좀 더 쉽게 사용하게 하는 도구
 - 라이브러리 및 버전 관리 자동화
 - 설정 자동화(@EnableAutoConfiguration)
 - 내장 Tomcat 사용
 - 독립적으로 실행가능한 JAR(내장 Tomcat 지원으로 jar로 패키징하여 웹 어플리케이션 실행 가능)

### Spring Boot Version

#### Spring Boot 2
 - <span style="color:white">Java8 + Spring Framework 5
 - 써드파티 라이브러리 업그레이드
 > Tomcat 8.5 / Flyway 5 / Hibernate 5.2 / Thymeleaf 3 / Elasticsearch 5.6 / Gradle 4 / Jetty 9.4 / Mockito 2.x
 - <span style="color:white">Reactive Spring : 한정된 자원에서 비동기(asynchronous) 넌블럭킹(non-blocking) 알고리즘을 이용하여 다수의 요청에도 빠르고 예측 가능한 응답을 실현
 - <span style="color:white">Functional APIs : 함수형 스타잉로 작성 가능, 스프링 기술과 어노테이션에서 분리된 코드
 - <span style="color:white">Kotlin 지원
 - <span style="color:white">Configuration properties : 프로퍼티를 읽을 때 양식이 통일
 > 엘리먼트 구분 "." / 영어 소문자 + 숫자 / 단어 구분자로 "-" 사용 가능
 - Spring Security
 - <span style="color:white">HikariCP : Database 커넥션 풀 관리 프레임워크 / Tomcat Pool > HikariCP

#### Spring Boot 2.1
 - 성능 향상
 - Spring Data JPA: bootstrap-mode
 - <span style="color:white">JDK 11 + Spring Framework 5.1
 - 주요 버전 업그레이드
 > Tomcat 9 / Undertow 2 / Hibernate 5.3 / JUnit 5.2 / Micrometer 1.1
 - Deprecations from 2.0
 - New Spring Actuator Endpoints
 - Logging Refinements
 - <span style="color:white">JUnit : JUnit5 사용 가능하지만 빌드 설정에 명시 필요
 - Bean Overriding

#### Spring Boot 2.2
 - 성능 향상
 > 속성 추가 : @Configuration, @SpringBootApplication, @SpringBootConfiguration
 - <span style="color:white">Java 13 + Spring Framework 5.2
 - 주요 버전 업그레이드
 > Spring HATEOAS 1.0 / Spring Kafka 2.3 / Spring Security 5.2 / Hamcrest 2.1 / Mockito 3.1 / AssertJ 3.12 / Elasticsearch 6.7 / Git Commit ID Plugin 3.0 / Hazelcast 3.12 / Jackson 2.10 / Jedis 3.1 / Lettuce 5.2
 - Deprecations from 2.1
 - <span style="color:white">Jakarta EE : javax. > jakarta. group ID 이동
 - <span style="color:white">Junit 5 : Junit 5 기본 제공
 - Gradle 4.10

#### Spring Boot 2.3
 - 새 버전의 배포 주기 계획 발표
 > 새 버전 6개월 주기로 고정
 - <span style="color:white">Java 14, Gradle 5.6.x ~ 6.3, developmentOnly Gradle 설정 자동 생성
 - 주요 버전 업그레이드 (R2DBC 지원)
 > Spring Data Neumann -> R2DBC 지원 [ R2DBC ConnectionFactory 자동 설정, health indicator, metrics, @DataR2dbcTest ]
 / Spring HATEOAS 1.1
 / Spring Integration 5.3
 / Spring Kafka 2.5
 / Spring Security 5.3
 / AssertJ 3.16, JUnit Jupiter 5.6, Mockito 3.3
 / Elasticsearch 7.6
 / Hibernate Validator 6.1
 / Jackson 2.11
 / QueryDSL 4.3
 / MongoDB 4.0
 - <span style="color:white">도커 지원
 > layered jars : jar 내용물을 나눠서, 업데이트가 있는 이미지만 빌드하는 방식, 도커 이미지를 더 효율적으로 작성하는데 도움
 - Validation Starter 를 web starter 에서 제외
 - Graceful shutdown
 - Jackson 2.11
 - Actuator 향상점
 - 기본 에러 페이지 내용 변화
 - @ActiveProfiles 에 여러 개의 프로파일 설정하기

#### Spring Boot 2.4
 - 버전 네이밍 변화
 > 2.3.0.RELEASE -> 2.4.0
 - <span style="color:white">Java 15, Startup Logging
 - Jar Optimizations
 - JUnit Vintage Engine
 - 설정 파일 처리 기능 변화 (.properties, .yaml)
 - Logback 설정 변경, Property Migrator
 - @ConstructorBinding (in 2.2) 과 발전사항
 - Origin chains
 - Docker, Buildpack 지원 내용 업데이트

#### Spring Boot 2.5
 - <span style="color:white">Java 16, Gradle 7, Jetty 10, Kotlin 1.5, Groovy 3
 - 디펜던시 업그레이드
 > Spring Data 2021.0
 / CrudRepository, ReactiveCrudRepository: deleteAllById() 가 새로 생김
 / Spring HATEOAS 1.3
 / Spring Integration 5.5
 / Spring Kafka 2.7
 / Spring Retry 1.3
 / Spring Security 5.5
 / Spring Session 2021.0
 / Jackson 2.12
 / Mockito 3.7
 / JUnit Jupiter 5.7
 / Elasticsearch 7.12
 - Deprecations from Spring Boot 2.3, 2.4, 2.5
 - SQL Script DataSource Initialization
 - 기본 에러 뷰의 메시지
 - Logging Shutdown Hooks
 - HTTP/2 over TCP (h2c)
 - R2DBC DB 초기화
 - Layered WARs, Docker Image Building Support
 - GET requests to actuator/startup
















