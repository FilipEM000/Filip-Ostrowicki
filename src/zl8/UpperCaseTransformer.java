package zl8;

public class UpperCaseTransformer implements Transformer<String, String> {

    @Override
    public String transform(String input) {
        return input == null ? null : input.toUpperCase();
    }
}
