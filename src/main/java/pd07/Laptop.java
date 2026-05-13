package pd07;

import java.math.BigDecimal;

public final class Laptop extends Resource {
    private static final BigDecimal ADDITIONAL_COST = new BigDecimal(10);

    private final double inch;

    public Laptop(String id, String name, BigDecimal price, Type type, double inch) {
        super(id, name, price, type);
        this.inch = inch;
    }

    public static Laptop of(String id, String name, BigDecimal price, double inch) {
        return new Laptop(id, name, price, Type.LAPTOP, inch);
    }

    @Override
    public BigDecimal calculatePricePerDay() {
        return ADDITIONAL_COST.add(super.getBasePrice());
    }
}
