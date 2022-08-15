import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExam {
    public static void main(String[] args) {
        List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

//        문제 2.1
//        List에 저장된 단어들의 접두사가 각각 몇개씩 있는지 Map<String, Integer>으로 변환하여라. ex) ("T", 1), ("a", 2) ...
//
//        문제 2.2
//        List에 저장된 단어들 중에서 단어의 길이가 2 이상인 경우에만, 모든 단어를 대문자로 변환하여 스페이스로 구분한 하나의 문자열로 합한 결과를 반환하여라. ex) ["Hello", "a", "Island", "b"] -> “HI”

        System.out.printf(quiz1(WORDS).toString());

        List<String> result = WORDS.stream()
                .map(w -> w.substring(0, 1))
                .collect(Collectors.toList());

        System.out.printf(result.toString());



    }

    public static Map<String, Integer> quiz1(List<String> WORDS) {
        return WORDS.stream()
                .map(w -> w.substring(0, 1))
                .collect(
                    Collectors.toMap(
                        prefix -> prefix, prefix -> 1,
                        (oldValue, newValue) -> (newValue += oldValue)
                    )
                );
    }

    public String quiz2(List<String> WORDS) {
        return WORDS.stream()
                .filter(w -> w.length() > 1)
                .map(String::toUpperCase)
                .map(w -> w.substring(0, 1))
                .collect(Collectors.joining(" "));
    }
}
