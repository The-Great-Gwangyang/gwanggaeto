# Java

---

## Java Lambda

### Lambda 특징

- 배경 : 함수형 프로그래밍 기법을 위해 Java 8에서 도입됨. 익명 함수(anonymous function)을 생성
- 특징

> - 람다식으로 인해 메서드를 변수처럼 다루는 것이 가능. 람다식은 메서드의 매개변수로 전달되는 것이 가능하고, 메서드의 결과로 반환될 수도 있음
> - 모든 메서드는 클래스에 포함되어야 하므로 클래스도 새로 만들어야 하고, 객체도 생성해야만 비로소 이 메서드를 호출할 수 있다. 그러나 람다식은 이 모든 과정 없이 오직 람다식 자체만으로도 이 메서드의 역할을 대신할 수 있다.

- 기존 & Lambda식 비교
```Java
// 기존의 방식
반환티입 메소드명 (매개변수, ...) {
	실행문
}

// 예시
public String hello() {
    return "Hello World!";
}
```

```Java
// 람다 방식
(매개변수, ... ) -> { 실행문 ... }

// 예시
() -> "Hello World!";
```

- 구조 : () -> {}; / (매개변수) -> {실행문} / 해당 interface의 매개변수, 실행문으로 구성
- 사용 조건 : 함수적 인터페이스(인터페이스가 단 한개의 추상 메소드를 정의하고 있는 인터페이스)인 경우에만 사용 가능

  > 인터페이스 구현 방법
  >
  > 1. 인터페이스를 작성하고 클래스로 구현해서 사용
  > 2. 인터페이스를 익명 함수로 구현해서 사용
  > 3. 람다식으로 사용
  >
- 사용 방법

```Java
public interface Calculator{
    public int cal(int num1,int num2);
}
```

1. 기본 사용법 : (매개변수 타입)->{};

```Java
public static void main (String[] args){
    Calculator cal = (int num1, int num2) -> {return num1+num2;}
    System.out.println(cal.cal(1,2));
}
```

2. 매개변수 타입 생략 : (매개변수)->{};

```Java
public static void main (String[] args){
    Calculator cal = (num1,num2) -> {return num1+num2;}
    System.out.println(cal.cal(1,2));
}
```

3. 매개변수가 없는 경우 : ()->{};

```Java
public static void main (String[] args){
    Calculator cal = () -> {System.out.println("매개변수가 없는 경우 입니다.");}
    cal.cal();
}
```

4. 중괄호 생략 : ()->;

```Java
public static void main (String[] args){
    Calculator cal = (num1,num2) -> num1+num2;
    System.out.println(cal.cal(1,2));
}
```

5. 소괄호 생략 : 매개변수 ->;

```Java
public static void main (String[] args){
    Calculator cal = num1 -> num1-1;
    System.out.println(cal.cal(1));
}
```

### 함수형 인터페이스란?

#### 특징

- 함수형 인터페이스 : 함수를 1급 객체처럼 다룰 수 있게 해주는 어노테이션, 인터페이스에 선언하여 단 하나의 추상 메소드만을 갖도록 제한하는 역할을 함.

- 기존 & Lambda식 비교
```Java
@FunctionalInterface
interface MyLambdaFunction {
    int max(int a, int b);
}
```
```Java
public class Lambda {
    public static void main(String[] args) {
        // 기존의 익명함수
        System.out.println(new MyLambdaFunction() {
            public int max(int a, int b) {
                return a > b ? a : b;
            }
        }.max(3, 5));
    }
}
```
```Java
public class Lambda {
    public static void main(String[] args) {
        // 람다식을 이용한 익명함수
        MyLambdaFunction lambdaFunction = (int a, int b) -> a > b ? a : b;
        System.out.println(lambdaFunction.max(3, 5));
    }
}
```
- 람다식으로 생성된 순수 함수는 함수형 인터페이스로만 선언이 가능

#### java.util.function

- java.util.function 사용 이유!!

> - 매번 새로운 함수형 인터페이스를 정의하지 않아도 된다.
> - 대부분의 메서드는 타입이 비슷하다. 매개변수가 없거나 한 개 또는 두 개, 반환 값은 없거나 한 개. 또한 지네릭 메서드로 정의하면 매개변수나 반환 타입이 달라도 문제가 되지 않는다.
> - 함수형 인터페이스에 정의된 메서드 이름이 통일되고, 재사용성이나 유지보수 측면에도 좋다.

- 기본형 함수형 인터페이스


| FuntionalInterface | method            | 설명                     |
| ------------------ | ----------------- | ------------------------ |
| java.lang.Runnable | void run()        | 매개변수, 반환 모두 없음 |
| Supplier<T>        | T get()           | 매개변수 없음, 반환 T    |
| Comsumer<T>        | void accept(T t)  | 매개변수 T, 반환 없음    |
| Function<T, R>     | R apply(T t)      | 매개변수 T, 반환 R       |
| Predicate<T>       | boolean test(T t) | 매개변수 T, 반환 boolean |

> Runnable : Runnable은 인자를 받지 않고 리턴값도 없는 인터페이스
>
> ```Java
> Runnable runnable = () -> System.out.println("run anything!");
> runnable.run();
> // 결과
> // run anything!
> ```

> Supplier : Supplier<T>는 인자를 받지 않고 T 타입의 객체를 리턴
>
> ```Java
> Supplier<String> getString = () -> "Happy new year!";
> String str = getString.get();
> System.out.println(str);
> // 결과
> // Happy new year!
> ```

> Consumer : Consumer<T>는 T 타입의 객체를 인자로 받고 리턴 값 없음
>
> ```Java
> Consumer<String> printString = text -> System.out.println("Miss " + text + "?");
> printString.accept("me");
> // 결과
> // Miss me?
> ```
>
> ```Java
> Consumer<String> printString = text -> System.out.println("Miss " + text + "?");
> Consumer<String> printString2 = text -> System.out.println("--> Yes");
> printString.andThen(printString2).accept("me");
> // 결과
> // Miss me?
> // --> Yes 
> ```

> Function : Function<T, R>는 T타입의 인자를 받고, R타입의 객체를 리턴
>
> ```Java
> Function<Integer, Integer> multiply = (value) -> value * 2;
> Integer result = multiply.apply(3);
> System.out.println(result);
> // 결과
> // 6
> ```
>
> ```Java
> Function<Integer, Integer> multiply = (value) -> value * 2;
> Function<Integer, Integer> add      = (value) -> value + 3;
>
> Function<Integer, Integer> addThenMultiply = multiply.compose(add);
>
> Integer result1 = addThenMultiply.apply(3);
> System.out.println(result1);
> // 결과
> // 12
> ```

> Predicate : Predicate<T>는 T타입 인자를 받고 결과로 boolean을 리턴
>
> ```Java
> Predicate<Integer> isBiggerThanFive = num -> num > 5;
> System.out.println("10 is bigger than 5? -> " + isBiggerThanFive.test(10));
> // 결과
> // 10 is bigger than 5? -> true
> ```
>
> ```Java
> Predicate<Integer> isBiggerThanFive = num -> num > 5;
> Predicate<Integer> isLowerThanSix = num -> num < 6;
> System.out.println(isBiggerThanFive.and(isLowerThanSix).test(10));
> System.out.println(isBiggerThanFive.or(isLowerThanSix).test(10));
> // 결과
> // false
> // true
> ```
>
> ```Java
> Predicate<String> isEquals = Predicate.isEqual("Google");
> isEquals.test("Google");
> // 결과
> // true
> ```

- 매개변수가 2개인 함수형 인터페이스 : 기본형인 Comsumer<T>, Predicate<T>, Function<T>를 매개변수 2개 받도록 변경한 인터페이스


| FuntionalInterface  | method                 | 설명                       |
| ------------------- | ---------------------- | -------------------------- |
| BiComsumer<T, U>    | void accept(T t, U u)  | 매개변수 2개, 반환 없음    |
| BiPredicate<T, U>   | boolean test(T t, U u) | 매개변수 2개, 반환 boolean |
| BiFunction<T, U, R> | R apply(T t, U u)      | 매개변수 2개, 반환 R       |

- 입력과 반환이 동일한 UnaryOperation, BinaryOperation

> - UnaryOperation는 기본형 Function<T>와 유사하게 매개변수 1개와 반환타입을 갖는데, 매개변수와 반환값이 동일한 경우에 사용
> - BinaryOperation는 BiFuntion<T, U, R>과 유사하게 매개변수 2개, 반환타입을갖는데 입력타입과 반환타입이 모두 동일한 경우 사용


| FuntionalInterface | method            | 설명                                   |
| ------------------ | ----------------- | -------------------------------------- |
| UnaryOperation<T>  | T apply(T t)      | 매개변수 1개로 반환타입과 동일         |
| BinaryOperation<T> | T apply(T t, T t) | 매개변수 2개, 매개변수와 반환유형 동일 |
