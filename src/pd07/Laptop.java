package pd07;

public non-sealed class Laptop extends Resource {
    private double inch;
    private static final double ADDITIONAL_COST = 200;

    public Laptop(String id, String name, double price, Type type, double inch) {
        super(id, name, price, type);
        this.inch = inch;
    }

    @Override
    public double getRentalCost() {
        return super.getPrice() + ADDITIONAL_COST;
    }
}
