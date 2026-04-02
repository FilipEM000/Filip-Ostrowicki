package pd09;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static <T> List<T> transform(List<T> list, StringConverter<T> transformer) {
        return list.stream()
                .map(transformer::convert)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> listOfStrings = List.of("Ad am ", "   KamIl", " M aci ej");

        StringConverter<String> removeSpaces = input -> input.replace(" ", "");
        StringConverter<String> toUpperLetters = String::toUpperCase;
        StringConverter<String> chopToLengthOf6 = input -> input.length() < 6 ? input : input.substring(0, 6);
        StringConverter<String> addPrefix = input -> "Prefix" + input;

        System.out.println(transform(listOfStrings, removeSpaces
                .and(toUpperLetters)
                .and(chopToLengthOf6)
                .and(addPrefix)
        ));
    }
}
