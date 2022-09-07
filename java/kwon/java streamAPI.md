# Java

---

## Java StreamAPI

### StreamAPI 특징

#### 1. 원본의 데이터를 변경하지 않는다.
- Stream API는 원본의 데이터를 조회하여, Stream을 생성하고 정렬, 필터링 등의 작업은 Stream에서 처리가 된다.

#### 2. 일회용이다.
- 한 번 사용 후 재사용이 불가능하다. 필요한 경우 다시 Stream을 생성해줘야 한다.

#### 3. 내부 반복으로 작업을 처리한다.
- 기존에는 반복의 경우 for, while등의 별도 문법을 사용했지만, Stream의 경우 메소드 내부에 숨기고 있어 보다 간결한 코드 작성이 가능.

### Stream API 연산 종류

- Stream 연산 예시 코드
```Java
List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

myList
    .stream()					// 생성하기
    .filter(s -> s.startsWith("c"))		// 가공하기
    .map(String::toUpperCase)			// 가공하기
    .sorted()					// 가공하기
    .count();					// 결과만들기
```

#### 1. 생성하기
- Collection의 Stream 생성
  - Collection 인터페이스에는 Stream()이 정의 되어 있기 때문에, Collection 인터페이스를 구현한 객체들(List, Set 등...)은 모두 Stream 생성 가능
  - stream()을 사용하면 해당 Collection의 객체를 소스로 하는 Stream을 반환
```Java
// List로부터 스트림을 생성
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> listStream = list.stream();
```
- 배열의 Stream 생성
  - Stream의 of 메소드 또는 Arrays의 stream 메소드를 사용하여 생성 가능
```Java
// 배열로부터 스트림을 생성
Stream<String> stream = Stream.of("a", "b", "c"); //가변인자
Stream<String> stream = Stream.of(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"}, 0, 3); //end범위 포함 x
```
- 원시 Stream 생성
  - int와 long 그리고 double과 같은 원시 자료형들을 사용하기 위한 특수한 종류의 Stream(IntStream, LongStream, DoubleStream) 들도 사용 가능
  - Intstream같은 경우 range()함수를 사용하여 기존의 for문을 대체 가능.
```Java
// 4이상 10 이하의 숫자를 갖는 IntStream
IntStream stream = IntStream.range(4, 10);
```

#### 2. 가공하기 - 중간 연산
- 필터링 / Filter
  - Stream에서 조건에 맞는 데이터만을 정제하여 더 작은 컬렉션을 만드는 연산.
  - Java에서는 filter 함수의 인자로 함수형 인터페이스 Predicate를 받고 있기 때문에, boolean을 반환하는 람다식을 작성하여 filter 함수를 구현할 수 있다.
```Java
Stream<String> stream = 
  list.stream()
  .filter(name -> name.contains("a"));
```
- 데이터 변환 / Map
  - 기존의 Stream 요소들을 변환하여 새로운 Stream을 형상하는 연산
  - 저장된 값을 특정한 형태로 변환하는데 주로 사용, Java에서는 map함수의 인자로 함수형 인터페이스 function을 받고 있다.
```Java
/* Map 예시 */
Stream<String> stream = 
  names.stream()
  .map(s -> s.toUpperCase());

/* Map 활용 */
Stream<File> fileStream = Stream.of(new File("Test1.java"), new File("Test2.java"), new File("Test3.java"));
//Stream<File> --> Stream<String> 변환
Stream<String> fileNameStream = fileStream.map(File::getName);
```
- 정령 / Sorted
  - 파라미터로 Comparator를 넘길 수 있다.
  - Comparator 인자 없이 호출할 경우 오름차순 정렬이 되고, Comparator의 reverseOrder를 이용하여 내림차순 정렬 가능.
```Java
List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

Stream<String> stream = list.stream()
  .sorted()
// [Go, Groovy, Java, Python, Scala, Swift]

Stream<String> stream = list.stream()
  .sorted(Comparator.reverseOrder())
// [Swift, Scala, Python, Java, Groovy, Go]
```
- 중복 제거 / Distinct
  - distinct는 중복된 데이터를 검사하기 위해 Objcet의 equals() 메소드를 사용.
```Java
List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");

Stream<String> stream = list.stream()
  .distinct()
// [Java, Scala, Groovy, Python, Go, Swift]
```
  - 단, 만약 우리가 생성한 클래스를 Stream으로 사용한다고 하면 equals와 hashCode를 오버라이드 해야만 distinct()를 제대로 적용할 수 있다.
```Java
import java.util.Objects;

public class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
```
```Java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("MangKyu");
        Employee e2 = new Employee("MangKyu");
        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);

        int size = employees.stream().distinct().collect(Collectors.toList()).size();
        System.out.println(size);
    }
}
```
- 특정 연산 수행 / Peek
  - Stream 요소들을 대상으로 Stream에 영향을 주지 않고 특정 연산을 수행할 때 사용.
  - 특정 작업을 수행할 뿐, 결과에 영향을 주지 않는다.
  - peek 함수는 파라미터로 함수형 인터페이스 Consumer를 인자로 받는다.
```Java
int sum = IntStream.of(1, 3, 5, 7, 9)
  .peek(System.out::println)
  .sum();
```
- 원시 Stream <-> Stream
  - 일반적인 Stream 객체는 mapToInt(), mapToLong(), mapToDouble()이라는 특수한 Mapping 연산을 지원
  - 원시객체는 mapToObject를 통해 일반적인 Stream 객체로 바꿀 수 있다.
```Java
// IntStream -> Stream<Integer>
IntStream.range(1, 4)
    .mapToObj(i -> "a" + i)

// Stream<Double> -> IntStream -> Stream<String>
Stream.of(1.0, 2.0, 3.0)
    .mapToInt(Double::intValue)
    .mapToObj(i -> "a" + i)
```

#### 3. 결과 만들기 - 최종 연산
- 최댓값,최솟값,총합,평균,갯수 / Max,Min,Sum,Average,Count
  - min, max, average는 Stream이 비어 있는 경우에 값을 특정할 수 없어 Optional로 값이 반환.
```Java
OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
int max = IntStream.of().max().orElse(0);
IntStream.of(1, 3, 5, 7, 9).average().ifPresent(System.out::println);
```
  - 총합이나 갯수의 경우, 값이 비어 있는 경우 0으로 특정할 수 있다.
  - sum, count의 경우 Optional이 아닌 원시 값을 반환하도록 구현
```Java
long count = IntStream.of(1, 3, 5, 7, 9).count();
long sum = LongStream.of(1, 3, 5, 7, 9).sum();
```
- 데이터 수집 / collect
  - Stream 요소들을 List, Set, Map등의 다른 종류 결과로 수집하고 싶을 경우 사용.
  - 어떻게 Stream의 요소들을 수집할 것인가를 정의한 Collector 타입을 인자로 받아서 처리
  - 자주 사용하는 작업은 Collectors 객체에서 static 메소드로 제공
  - 하는 것이 없는 경우에는 Collector 인터페이스를 직접 구현
```Java
collect() : 스트림의 최종연산, 매개변수로 Collector를 필요로 한다.
Collector : 인터페이스, collect의 파라미터는 이 인터페이스를 구현해야한다.
Collectors : 클래스, static메소드로 미리 작성된 컬렉터를 제공한다.

// collect의 파라미터로 Collector의 구현체가 와야 한다.
Object collect(Collector collector)
```
> 1. Collectors.toList()
> - Stream에서 작업한 결과를 List로 반환
> ```Java
> List<String> nameList = productList.stream()
>   .map(Product::getName)
>   .collect(Collectors.toList());
> ```
> 2. Collectors.joining()
> - Stream에서 작업한 결과를 1개의 String으로 이어 붙일 때 사용
> - delimiter : 각 요소 중간에 들어가 요소를 구분시켜주는 구분자
> - prefix : 결과 맨 앞에 붙는 문자
> - suffix : 결과 맨 뒤에 붙는 문자
> ```Java
> String listToString = productList.stream()
> 	.map(Product::getName)
> 	.collect(Collectors.joining());
> // potatoesorangelemonbreadsugar
> 
> String listToString = productList.stream()
> .map(Product::getName)
> .collect(Collectors.joining(" "));
> // potatoes orange lemon bread sugar
> 
> String listToString = productList.stream()
> .map(Product::getName)
> .collect(Collectors.joining(", ", "<", ">"));
> // <potatoes, orange, lemon, bread, sugar>
> ```
> 3. Collectors.averagingInt(), Collectors.summingInt(), Collectors.summarizingInt()
> - 평균, 총합을 구하는 경우 사용
> ```Java
> Double averageAmount = productList.stream()
> 	.collect(Collectors.averagingInt(Product::getAmount));
> 
> // 86
> Integer summingAmount = productList.stream()
>   .collect(Collectors.summingInt(Product::getAmount));
> ```
> 4. Collectors.groupingBy()
> - 작업한 결과를 특정 그룹으로 묶을 때 사용.
> - 매개변수로 함수형 인터페이스 Function을 필요
> - 결과는 Map으로 반환 받는다.
> ```Java
> Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
>   .collect(Collectors.groupingBy(Product::getAmount));
> /*
> {23=[Product{amount=23, name='potatoes'}, Product{amount=23, name='bread'}],
>  13=[Product{amount=13, name='lemon'}, Product{amount=13, name='sugar'}],
>  14=[Product{amount=14, name='orange'}]}
> */
> ```
> 5. Collectors.partitioningBy()
> - 함수형 인터페이스 Predicate를 받아 Boolean을 Key값으로 partitioning
> ```Java
> Map<Boolean, List<Product>> mapPartitioned = productList.stream()
>	.collect(Collectors.partitioningBy(p -> p.getAmount() > 15));
> /*
> {false=[Product{amount=14, name='orange'}, Product{amount=13, name='lemon'}, Product{amount=13, name='sugar'}],
>  true=[Product{amount=23, name='potatoes'}, Product{amount=23, name='bread'}]}
> */
> ```
- 조건 검사 / Match
  - Stream 요소들이 특정 조건을 충족하는지 검사할 때 사용
  -  match 함수는 함수형 인터페이스 Predicate를 받아서 해당 조건을 만족하는지 검사를 하게 되고, 검사 결과를 boolean으로 반환한다.
  > - anyMatch: 1개의 요소라도 해당 조건을 만족하는가
  > - allMatch: 모든 요소가 해당 조건을 만족하는가
  > - nonMatch: 모든 요소가 해당 조건을 만족하지 않는가
```Java
// 모두 True 반환
List<String> names = Arrays.asList("Eric", "Elena", "Java");

boolean anyMatch = names.stream()
    .anyMatch(name -> name.contains("a"));
boolean allMatch = names.stream()
    .allMatch(name -> name.length() > 3);
boolean noneMatch = names.stream()
    .noneMatch(name -> name.endsWith("s"));
```
- 특정 연산 수행 / forEach
  - 특정 연산을 수행하고 싶은 경우 사용
  - peek()와 비슷함
  - 최종 연산으로 실제 요소들에 영향을 줄 수 있다.
  - 반환값이 존재하지 않는다.
```Java
names.stream()
    .forEach(System.out::println);
```

### Stream API 고급 활용

#### FlatMap 활용 (중첩 구조 제거)
- 중첩 구조를 한 단계 제거하기 위한 중간 연산
- Function 함수형 인터페이스를 매개 변수로 받는다.
- 2중 리스트가 존재한다고 할 때, 이를 1중 리스트로 변환하기 위해서 flatMap을 사용 가능
```Java
// List에서 활용
// flatMap 함수
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

// [[a], [b]]
List<List<String>> list = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));

// [a, b]
List<String> flatList = list.stream()
  .flatMap(Collection::stream)
  .collect(Collectors.toList());
```
```Java
// 배열에서 활용
Stream<String[]> strStream = Stream.of(
    new String[] {"a", "b", "c"}, 
    new String[] {"d", "e", "f"});

// map을 사용하면 2중 Stream이 반환됨
Stream<Stream<String>> stream = strStream.map(Arrays::stream);

// flatMap을 사용하면 1중 Stream으로 차원을 낮출 수 있음
Stream<String> stream = strStream.flatMap(Arrays::stream);
```
- 동작 방식 예제 1
```Java
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Student {
    private int kor;
    private int eng;
    private int math;

    public Student(int kor, int eng, int math) {
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    public int getKor() {
        return kor;
    }

    public int getEng() {
        return eng;
    }

    public int getMath() {
        return math;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(80, 90, 75),
                new Student(70, 100, 75),
                new Student(85, 90, 85),
                new Student(80, 100, 90)
        );
        students.stream().flatMapToInt(student ->
                IntStream.of(student.getKor(), student.getEng(), student.getMath()))
                .average()
                .ifPresent(avg -> System.out.println(Math.round(avg * 10) / 10.0));
    }
}
```
- 동작 방식 예제 1
```Java
class Outer {
    Nested nested;
}
class Nested {
    Inner inner;
}
class Inner {
    String foo;
}

// flatMap 적용 전
Outer outer = new Outer();
if (outer != null && outer.nested != null && outer.nested.inner != null) {
    System.out.println(outer.nested.inner.foo);
}

// flatMap 적용 후
Optional.of(new Outer())
        .flatMap(o -> Optional.ofNullable(o.nested))
        .flatMap(n -> Optional.ofNullable(n.inner))
        .flatMap(i -> Optional.ofNullable(i.foo))
        .ifPresent(System.out::println);
```

#### Reduce를 통한 결과 생성
- 누산기(Accumulator)와 연산(Operation)으로 컬렉션에 있는 값을 처리하여 더 작은 컬렉션이나 단일 값을 만드는 작업
- 최대 3가지 매개변수 가질 수 있다.
> - Accumulator: 각 요소를 계산한 중간 결과를 생성하기 위해 사용
> - Identity: 계산을 처리하기 위한 초기값
> - Combiner: Parlallel Stream에서 나누어 계산된 결과를 하나로 합치기 위한 로직
1. reduce(accumulator) - 매개변수 1개인 경우
```Java
// 1개 (accumulator)
Optional<T> reduce(BinaryOperator<T> accumulator);

OptionalInt reduced = IntStream.range(1, 4) // [1, 2, 3]
                .reduce((a, b) -> {
                    return Integer.sum(a, b);
                });
// 결과 : 6(1+2+3)
```
2. reduce(identity, accumulator) - 매개변수 2개인 경우
```Java
// 2개 (identity, accumulator)
T reduce(T identity, BinaryOperator<T> accumulator);

int reduced = IntStream.range(1, 4) // [1, 2, 3]
                .reduce(10, (a, b) -> {
                    return Integer.sum(a, b);
                });
// 결과 : 16(10 + 1+2+3)
```
3. reduce(identity, accumulator, combiner) - 매개변수 3개인 경우
```Java
// 3개
<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
```
```Java
// Parallel Stream이 아니기 때문에 Combiner 관련 출력이 찍히지 않는다.
int reduced = Stream.of(1, 2, 3)
         .reduce(10, Integer::sum, (a, b) -> {
             System.out.println("combiner was called");
             return a + b;
         });
```
```Java
// 초기값이 모든 reduce 단계에 필요한 경우 Identity에 추가
int reduced = Stream.of(1, 2, 3)
        .parallel()
        .reduce(10, Integer::sum, (a, b) -> {
            System.out.println("combiner was called");
            return a + b;
        });
// 결과 : 36 (10+1 + 10+2 + 10+3) > 역순으로 더하게 되어 12 + 13 >> 25 + 11
```
```Java
int reduced = 10 + Stream.of(1, 2, 3)
        .parallel()
        .reduce(0, Integer::sum, (a, b) -> {
            System.out.println("combiner was called");
            return a + b;
        });
// 결과 : 16
```

#### Null-Safe한 Stream 생성
- Java8부터 Optional이라는 Wrapper 클래스를 통해 Null 관련 코드를 가독성있게 처리 가능
```Java
List<String> nullList = null;

// NPE 발생
nullList.stream()
  .filter(str -> str.contains("a"))
  .map(String::length)
  .forEach(System.out::println);
// 결과 : NPE!

// collectionToStream 활용
// 빈 Stream으로 처리
collectionToStream(nullList)
  .filter(str -> str.contains("a"))
  .map(String::length)
  .forEach(System.out::println);
// 결과 : []
```

#### 실행 순서에 대한 고려
- Stream API를 정확히 알지 못하고 상용하면 처리 속도 지연이 생긴다.
- 예제 1
```Java
Stream.of("a", "b", "c", "d", "e")
        .filter(s -> {
            System.out.println("filter: " + s);
            return true;
        })
        .forEach(s -> System.out.println("forEach: " + s));
// 모든 데이터에 대해 filter가 진행되고 forEach가 수행되는 게 아니라...
// filter와 forEach 수행이 반복된다
/*
filter: a
forEach: a
filter: b
forEach: b
filter: c
forEach: c
filter: d
forEach: d
filter: e
forEach: e
*/
```
- 예제 2
```Java
Stream.of("a", "b", "c", "d", "e")
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .anyMatch(s -> {
            System.out.println("anyMatch: " + s);
            return s.startsWith("A");
        });
// 기대 횟수는 map 5번 + anyMatch 1번 총 6번 이지만...
// 실제는 map 1번 + anyMatch 1번으로 총 2번 실행된다.
/*
map: a
anyMatch: A
*/
```
- 예제 3-1
```Java
tream.of("a", "b", "c", "d", "e")
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("A");
        })
        .forEach(s -> System.out.println("forEach: " + s));
/*
map: a
filter: A
forEach: A
map: b
filter: B
map: c
filter: C
map: d
filter: D
map: e
filter: E
*/
```
- 예제 3-2
```Java
Stream.of("a", "b", "c", "d", "e")
        .filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("a");
        })
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .forEach(s -> System.out.println("forEach: " + s));
/*
filter: a
map: a
forEach: A
filter: b
filter: c
filter: d
filter: e
*/
```

#### 병렬 스트림(Parallel Stream)의 활용
- Stream은 아주 많은 양의 데이터를 처리해야 하는 경우에 런타임 성능을 높이기 위해 병렬로 실행할 수 있는 기능인 병렬 스트림(Parallel Stream)을 제공
- Parallel Stream은 내부적으로 fork & join을 사용하며, 
- ForkJoinPool.commonPool()을 통해 사용가능한 공통의 ForkJoinPool의 갯수를 확인가능하다.
- <참고> 내재되어 있는 ThreadPool의 갯수는 최대 5개이며, 사용가능한 물리적인 CPU 코어의 수에 따라 다르게 설정가능.
- 예제 1
```Java
Arrays.asList("a", "b", "c", "d", "e")
        .parallelStream()
        .filter(s -> {
            System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
            return true;
        })
        .map(s -> {
            System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
            return s.toUpperCase();
        })
        .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
/*
filter: c [main]
filter: e [ForkJoinPool.commonPool-worker-3]
map: e [ForkJoinPool.commonPool-worker-3]
filter: a [ForkJoinPool.commonPool-worker-2]
map: a [ForkJoinPool.commonPool-worker-2]
filter: b [ForkJoinPool.commonPool-worker-1]
forEach: A [ForkJoinPool.commonPool-worker-2]
forEach: E [ForkJoinPool.commonPool-worker-3]
map: c [main]
filter: d [ForkJoinPool.commonPool-worker-2]
map: d [ForkJoinPool.commonPool-worker-2]
map: b [ForkJoinPool.commonPool-worker-1]
forEach: D [ForkJoinPool.commonPool-worker-2]
forEach: C [main]
forEach: B [ForkJoinPool.commonPool-worker-1]
*/
```
- 예제 2
```Java
Arrays.asList("a", "b", "c", "d", "e")
        .parallelStream()
        .filter(s -> {
            System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
            return true;
        })
        .map(s -> {
            System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
            return s.toUpperCase();
        })
        .sorted((s1, s2) -> {
            System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
            return s1.compareTo(s2);
        })
        .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
/*
filter: c [main]
map: c [main]
filter: e [ForkJoinPool.commonPool-worker-3]
map: e [ForkJoinPool.commonPool-worker-3]
filter: a [ForkJoinPool.commonPool-worker-2]
map: a [ForkJoinPool.commonPool-worker-2]
filter: b [ForkJoinPool.commonPool-worker-1]
map: b [ForkJoinPool.commonPool-worker-1]
filter: d [main]
map: d [main]
sort: B <> A [main]
sort: C <> B [main]
sort: D <> C [main]
sort: E <> D [main]
forEach: C [main]
forEach: E [ForkJoinPool.commonPool-worker-2]
forEach: A [ForkJoinPool.commonPool-worker-1]
forEach: B [ForkJoinPool.commonPool-worker-3]
forEach: D [main]
*/
```



















