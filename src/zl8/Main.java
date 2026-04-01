package zl8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(10, "sad");

        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair.swap());

        List<Integer> integers = new ArrayList<>(Arrays.asList(5, 7, 8, 10, 6));
        IntegerToStringTransformer integerToStringTransformer = new IntegerToStringTransformer();
        System.out.println(transformAll(integers, integerToStringTransformer));

        List<String> strings = new ArrayList<>(Arrays.asList("ada", "kamil", "wojtek"));
        UpperCaseTransformer upperCaseTransformer = new UpperCaseTransformer();
        System.out.println(transformAll(strings, upperCaseTransformer));
    }

    private static <T, R> List<R> transformAll(List<T> input, Transformer<T, R> transformer) {
        List<R> result = new ArrayList<>();
        for (T element : input) {
            result.add(transformer.transform(element));
        }
        return result;
    }
}
