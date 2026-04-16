package pd10;

import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Getter
    private String name;
    private TransportType preferredTransport;
    private BigDecimal budget;

    public static User of(String name, TransportType preferredTransport, BigDecimal budget) {
        return new User(name, preferredTransport, budget);
    }

    public Optional<TransportType> getPreferredTransport() {
        return Optional.ofNullable(preferredTransport);
    }

    public Optional<BigDecimal> getBudget() {
        return Optional.ofNullable(budget);
    }
}
