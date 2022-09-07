package Quiz3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Answer3 {

    public static void main(String[] args) {

        System.out.println(quiz1().toString());
        System.out.println(quiz2());
    }

    private static final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    private static final List<Integer> numbers2 = Arrays.asList(3, 4);

    //    문제 3.1
    //    위와 같은 숫자 리스트가 있을 때 모든 숫자 쌍의 배열 리스트를 반환하여라.
    //    ex) numbers1 = [1,2,3], numbers2 = [3,4] -> [(1,3), (1,4), (2,3), (2,4), (3,3), (3,4)]
    public static List<Integer[]> quiz1() {
        return numbers1.stream()
                .flatMap(n -> numbers2.stream().map(
                                m -> new Integer[]{n, m}
                        )
                )
                .collect(Collectors.toList());
    }

    //    문제 3.2
    //    위와 같은 숫자 리스트가 있을 때 모든 숫자 쌍의 곱이 가장 큰 값을 반환하여라.
    //    ex) numbers1 = [1,2,3], numbers2 = [3,4] -> 12
    public static int quiz2() {
        return numbers1.stream()
                .flatMap(n -> numbers2.stream().map(m -> new Integer[]{n, m}))
                .mapToInt(a -> a[0] * a[1])
                .max().orElse(0);
    }

}
