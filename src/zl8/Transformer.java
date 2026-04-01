package zl8;

public interface Transformer<T, R> {
    R transform(T input);
}
