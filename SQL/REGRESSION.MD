# Regression
> 종류
 - REGR_COUNT : Y, X 둘 다 NULL이 아닌경우 카운트 시작
 - REGR_SLOPE : 직선의 기울기 계산
 - REGR_INTERCEPT : 수학의 Y축 계산
 - REGR_R2 : determination의 갯수

> DB2
![image](https://user-images.githubusercontent.com/28727696/198534610-57840140-6851-4ddd-b1a3-a6858bf7718a.png)
 참조) https://www.ibm.com/docs/ko/db2/11.1?topic=af-regression-functions-regr-avgx-regr-avgy-regr-count

> impala
 - !제공하는 regression 함수 없음 > 따로 구현하여 사용
 - REGR_COUNT() : SUM (case when a IS NOT NULL)
 - REGR_SLOPE : COVAL_POP(X,Y)/VAR_POP(X,Y)
 - REGR_INTERCEPT : AVG(Y)-REGR_SLOPE(Y,X)*AVG(X)
 - REGR_R2 : NULL IF VAR_POP(X)=0, ELSE IF VAR_POP(Y)=0 AND VAR_POP(X) <> 0, ELSE POWER(CORR(Y,X),2)
