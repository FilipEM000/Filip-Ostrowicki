package pd07;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter

public abstract sealed class Resource implements Comparable<Resource> permits Laptop, Console {
    private final String id;
    private final String name;
    private final BigDecimal basePrice;
    private final Type type;
    private static int numberOfResources = 0;

    public Resource(String id, String name, BigDecimal basePrice, Type type) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.type = type;
        numberOfResources++;
    }

    public abstract BigDecimal calculatePricePerDay();

    @Override
    public int compareTo(Resource resource) {
        return this.basePrice.compareTo(resource.getBasePrice());
    }
}


