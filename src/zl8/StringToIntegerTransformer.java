package zl8;

public class StringToIntegerTransformer implements Transformer<String, Integer> {

    @Override
    public Integer transform(String input) {
        return input == null ? null : Integer.parseInt(input);
    }
}
