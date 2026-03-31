package pd07;

import java.math.BigDecimal;

public final class Console extends Resource {
    private static final BigDecimal ADDITIONAL_COST = new BigDecimal(100);

    private final String model;

    public Console(String id, String name, BigDecimal price, Type type, String model) {
        super(id, name, price, type);
        this.model = model;
    }

    public static Console of(String id, String name, BigDecimal price, String model) {
        return new Console(id, name, price, Type.CONSOLE, model);
    }

    @Override
    public BigDecimal getRentalCost() {
        return super.getDefaultPrice().add(ADDITIONAL_COST);
    }
}
