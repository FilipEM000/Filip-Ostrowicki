package pd10;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Destination {
    private String name;
    private String country;

    public static Destination of(String name, String country) {
        return new Destination(name, country);
    }
}
