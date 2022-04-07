# 람다식(Lambda expression)
## 함수형 프로그래밍과 람다식
- 자바 = 객체 지향 프로그래밍 : 기능 수행 위해서 객체 만들고 그 내부에 변수 선언해 기능 수행하는 메서드 구현
- 람다식 : 함수형 프로그래밍 방식, 함수 구현과 호출만으로 프로그래밍 수행 됨
- 함수형 프로그래밍(Functional Programming:FP)
  순수함수(매개변수만 사용하는 함수) 구현, 호출함으로써 외부 자료에 부수적인 영향 주지X
  함수 수행되더라도 외부에는 영향 주지 않음

## 람다식 문법
- 두 수를 입력 받아 더하는 add() 함수 예

``` 
(int x, int y) -> {return x+y;}

```
  매개변수 1개인 경우 자료형, 괄호 생략 가능
 ```
str->{System.out.println(str);}
```
  매개변수가 두 개이상인 경우 괄호를 생략 X
```
x, y -> {System.out.println(x+y);}  //오류
```
  실행문이 한 문장인 경우 중괄호 생략 가능
```
str-> System.out.println(str);
```
  실행문이 한 문장이라도 return은 중괄호 생략 X
```
str-> return str.length();  //오류
```
  실행문이 한 문장의 반환문인 경우 return과 중괄호 모두 생략 
```
(x, y) -> x+y;
str -> str.length;
```

# 함수형 인터페이스와 람다식 구현하여 사용하기
## 함수형 인터페이스 선언
- 람다식 선언 위한 인터페이스
- @FunctionalInterface 애노테이션(annotation)

  함수형 인터페이스라는 의미, 내부에 여러 개의 메서드를 선언하면 에러남 

- 람다식 구현과 호출

```
public class TestMyNumber {

	public static void main(String[] args) {
		MyNumber max = (x, y)->(x>= y)? x:y; // 람다식을 인터페이스 자료형 max 변수에 대입

		System.out.println(max.getMax(10, 20));// 인터페이스 자료형 변수로 함수 호출

	}
}
```

#스트림(Stream)
##스트림이란
- 자료의 대상과 관계없이 동일 연산 수행
  배열, 컬렉션 대상으로 연산 수행, 일관성 있는 연산으로 자료 처리 쉽고 간단하게 함, 자료 처리 추상화 구현 O
- 스트림 재사용 X -> 스트림 생성해 연산 수행하면 스트림 소모됨, 다른 연산 수행 위해서는 다시 생성
- 스트림연산 -> 기존 자료 변경 X, 스트림이 사용하는 메모리 공간 별도 생성되므로
- 중간연산, 최종연산으로 구분됨
  중간 연산은 여러 개 연산 적용 OK, 최종 연산은 마지막에 한 번만 적용
  최종 연산 호출돼야 중간 연산 수행 따라서 중간 연산 결과를 연산 중에 알 수 X = 지연 연산

##스트림 생성하고 사용하기
- 정수 배열에 스트림 생성해 연산 수행하는 예
```
public class IntArrayTest {

	public static void main(String[] args) {

		int[] arr = {1,2,3,4,5};

		int sumVal = Arrays.stream(arr).sum();
		long count = Arrays.stream(arr).count();

		System.out.println(sumVal);
		System.out.println(count);
	}

}
```
## 중간 연산과 최종 연산
- 중간 연산의 예 - filter(), map(), sorted() 등
  조건에 맞는 요소를 추출(filter)하거나 요소를 변환 함(map)
- 최종 연산이 호출될 때 중간 연산이 수행되고 결과가 생성 됨
- 문자열 리스트에서 문자열의 길이가 5 이상인 요소만 출력하기
```
  sList.stream().filter(s->s.length() >= 5).forEach(s->System.out.println(s));
```
   filter()는 중간 연산, forEach()는 최종 연산

- 중간 연산과 최종 연산에 대한 구현은 람다식을 활용함
- 최종 연산의 예 - forEach(), count(), sum() 등
  스트림이 관리하는 자료를 하나씩 소모해가며 연산 수행
  최종 연산 후에 스트림은 다른 연산을 적용할 수 없음
  forEach() : 요소를 하나씩 꺼내 옴
  count() : 요소의 개수
  sum() : 요소들의 합

## ArrayList 객체에 스트림 생성하고 사용하기
- ArrayList에 문자열 자료(이름)을 넣고 이에 대한 여러 연산을 수행해보기
```
public class ArrayListStreamTest {

	public static void main(String[] args) {
		List<String> sList = new ArrayList<String>();
		sList.add("Tomas");
		sList.add("Edward");
		sList.add("Jack");

		Stream<String> stream = sList.stream();
		stream.forEach(s->System.out.print(s + " "));
		System.out.println();

		sList.stream().sorted().forEach(s->System.out.print(s+ " "));
		sList.stream().map(s->s.length()).forEach(n->System.out.println(n));
		sList.stream().filter(s->s.length() >= 5).forEach(s->System.out.println(s));

	}

}
```
- 새로운 연산을 수행하기 위해서는 기존의 스트림은 재사용할 수 없고 stream()메서드를 호출하여 스트림을 다시 생성해야 함


## 자바의 입출력을 위한 I/O 스트림
## 입출력 스트림
- 자바는 다양한 입출력 장치에 독립적으로 일관성있는 입출력을 입출력스트림 통해 제공함
- 입출력 구현되는 곳 : 파일 디스크, 키보드, 마우스, 네트웍, 메모리 등 모든 자료가 입력되고 출력되는 곳

## 입출력 스트림의 구분
- 대상 기준 : 입력 스트림 / 출력 스트림
- 자료의 종류 : 바이트 스트림 / 문자 스트림
- 기능 : 기반 스트림 / 보조 스트림

## 입력 스트림과 출력 스트림
- 입력 스트림 : 대상으로부터 자료를 읽어 들이는 스트림
- 출력 스트림 : 대상으로 자료를 출력하는 스트림

| 종류 | 예시 |
| ------ | ------ |
| 입력 스트림 | FileInputStream, FileReader, BufferedInputStream, BufferedReader 등 |
| 출력 스트림 | FileOutputStream, FileWriter, BufferedOutputStream, BufferedWriter 등 |

## 바이트 단위 스트림과 문자 단위 스트림
- 바이트 단위 스트림 : 동영상, 음악 파일, 실행 파일등의 자료를 읽고 쓸 때 사용
- 문자 단위 스트림 : 바이트 단위로 자료를 처리하면 문자는 깨짐, 인코딩에 맞게 2바이트 이상으로 처리하도록 구현된 스트림 

| 종류 | 예시 |
| ------ | ------ |
| 바이트 스트림 | FileInputStream, FileOutputStream, BufferedInputStream, BufferedOutputStream 등 |
| 문자 스트림 | FileReader, FileWriter, BufferedReader, BufferedWriter 등 |

## 기반 스트림과 보조 스트림
- 기반 스트림 : 대상에 직접 자료를 읽고 쓰는 기능의 스트림
- 보조 스트림 : 직접 읽고 쓰는 기능은 없이 추가적인 기능을 더해주는 스트림
- 보조 스트림은 직접 읽고 쓰는 기능은 없으므로 항상 기반 스트림이나 또 다른 보조 스트림을 생성자의 매개 변수로 포함

| 종류 | 예시 |
| ------ | ------ |
| 기반 스트림 | FileInputStream, FileOutputStream, FileReader, FileWriter 등 |
| 보조 스트림 | InputStreamReader, OutputStreamWriter, BufferedInputStream, BufferedOutputStream 등 |
