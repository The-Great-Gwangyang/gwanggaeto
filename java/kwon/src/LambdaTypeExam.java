import java.util.ArrayList;
import java.util.List;

public class LambdaTypeExam {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("C");
        list.add("React");

        for (String str : list) {
            System.out.printf("str");
        }

        list.stream().forEach((String str) -> System.out.println(str));
        list.stream().forEach(str -> System.out.println(str));
        list.stream().forEach(System.out::println);
        list.forEach(System.out::println);


    }
}
