package pd09;

@FunctionalInterface
public interface StringConverter<T> {
    T convert(T input);

    default StringConverter<T> and(StringConverter<T> other) {
        return input -> other.convert(this.convert(input));
    }
}
