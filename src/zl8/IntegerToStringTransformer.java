package zl8;

public class IntegerToStringTransformer implements Transformer<Integer, String> {

    @Override
    public String transform(Integer input) {
        return input == null ? null : String.valueOf(input);
    }
}
