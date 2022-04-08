# ORACLE

- 테이블 구성 확인 명령어 : desc

- 단일행 함수
  - 숫자함수 : mod(나머지), round(반올림), trunc(내림), ceil(올림)
  - 문자함수 : lower, upper, length, substr, ltrim, rtrim
  - 날짜함수 : sysdate(시스템날짜), add_months, month_between(월~월 사이 시간), last_day(해달 달의 마지막날)

- 변환함수
 1. 암시적(implict)변환 : 자동 
    - 튜닝에서 뜻하지 않은 속도 저하의 원인이 될 수 있음
    - VARCHAR OR CHAR --> NUMBER or DATE, NUMBER --> VARCHAR2M DATE --> VARCHAR2 그 외에는 error
    - ex)
    ```
    Select 1 + '1' From Dual;
    => 결과 : 2
    => 숫자 + 문자 계산이지만 자동으로 문자를 숫자로 바꾼 후 연산
    ```
 2. 명시적(explict)변환 : 강제
    - TO_CHAR(원래날짜, '원하는모양')
    * 'RRRR' : 2000년 이후에 Y2K 버그로 인해 등장한 새로운 날짜 표기법 *
    - TO_CHAR(숫자를 문자로)
      종류 | 의미 | 예시 | 결과
      :--:|:--:|:--:|:--:
      9 | 9 하나당 1자리 의미 | TO_CHAR(1234, '99999') | 1234
      0 | 빈자리를 0으로 표시 | TO_CHAR(1234, '099999') | 001234
      $ | $ 붙여서 표시 | TO_CHAR(1234, '$9999') | $1234
      . | 소수점 이하 표시 | TO_CHAR(1234, '9999.99') | 1234.00
      , | 천 단위 구분 기호 표시 | TO_CHAR(12345, '99.999') | 12,345    
    - TO_NUMBER()
    - TO_DATE('문자', '날짜포맷')

- PARTITION BY
  - GROUP BY 절과 동일한 역할. 단 GROUP BY 절을 사용하지 않고 필요한 집합으로 행들을 그룹화 시킴. 다양한 GRUOPING 집합의 집계 결과들을 함께 출력 가능
  -
 
- SYNONYM
  - 오라클 객체(테이블, 뷰, 시퀀스, 프로시저)에 대한 대체이름(Alias)
  - 데이터베이스 투명성 제공, 다른 유저 객체 참조시 자주 사용
  
 - 조회
  1. 테이블 조회
      - 테이블 목록 조회
      ```
      Select * From all_all_tables
      Select * From dba_tables
      Select * From dba_tables
      ```
      - 테이블 코멘트 조회      
      ```
      Select * From ALL_TAB_COMMENTS Where TABLE_NAME = '테이블명'
      Select * From USER_TAB_COMMENTS
      ```


  2. 컬럼 조회
      - 컬럼 조회      
      ```
      Select * From COLS Where TABLE_NAME = '테이블명'
      Select * From ALL_TAB_COLUMNS Where TABLE_NAME = '테이블명'
      Select * From USER_TAB_COLUMNS
      ```
      - 컬럼 코멘트 조회      
      ```
      Select * From USER_COL_COLUMNS
      ```

  참고)  
  - https://urakasumi.tistory.com/60
  - https://kshmc.tistory.com/entry/ORACLE-3-%EB%8B%A8%EC%9D%BC%ED%96%89-%ED%95%A8%EC%88%98-%ED%98%95%EB%B3%80%ED%99%98-%ED%95%A8%EC%88%98
  
