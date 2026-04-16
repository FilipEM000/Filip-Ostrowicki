package pd10;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Trip {
    private Destination destination;
    private TransportType transport;
    private BigDecimal price;
    private int duration;

    public static Trip of(Destination destination, TransportType transport, BigDecimal price, int duration) {
        return new Trip(destination, transport, price, duration);
    }
}
