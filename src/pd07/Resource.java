package pd07;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter

public abstract sealed class Resource implements Comparable<Resource> permits Laptop, Console {
    private final String id;
    private final String name;
    private final BigDecimal defaultPrice;
    private final Type type;
    private static int numberOfResources = 0;

    public Resource(String id, String name, BigDecimal defaultPrice, Type type) {
        this.id = id;
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.type = type;
        numberOfResources++;
    }

    public abstract BigDecimal getRentalCost();

    @Override
    public int compareTo(Resource resource) {
        return this.defaultPrice.compareTo(resource.getDefaultPrice());
    }
}


