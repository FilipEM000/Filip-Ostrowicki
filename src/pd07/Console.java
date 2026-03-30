package pd07;

public non-sealed class Console extends Resource {
    private String model;
    private static final double ADDITIONAL_COST = 50;

    public Console(String id, String name, double price, Type type, String model) {
        super(id, name, price, type);
        this.model = model;
    }

    @Override
    public double getRentalCost() {
        return super.getPrice() + ADDITIONAL_COST;
    }
}
