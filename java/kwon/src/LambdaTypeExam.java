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

    interface ChangeNumber {
        int increase(int n);
    }

    public static class LambdaTestBefore {
        public static void main(String[] args) {
            ChangeNumber c = new ChangeNumber() {
                @Override
                public int increase(int n) {
                    return n + 10;
                }
            };
            printChangeNumber(c, 7);
            printChangeNumber(c, 9);
            printChangeNumber(new ChangeNumber() {
                @Override
                public int increase(int n) {
                    return n + 5;
                }
            }, 10); //10을 5 만큼 증가하도록 하는 ChangeNumber 객체를 넘겨줌
        }
        static void printChangeNumber(ChangeNumber changeNumber, int num) {
            System.out.println(changeNumber.increase(num));
        }
    }

    public static class LambdaTestAfter {
        public static void main(String[] args) {
            ChangeNumber c = a -> a + 10;
            printChangeNumber(c, 7);
            printChangeNumber(c, 9);
            printChangeNumber(x -> x + 5, 10); //5를 증가하도록 하는 ChangeNumber 객체를 넘겨줌
        }
        static void printChangeNumber(ChangeNumber changeNumber, int num) {
            System.out.println(changeNumber.increase(num));
        }
    }
}
