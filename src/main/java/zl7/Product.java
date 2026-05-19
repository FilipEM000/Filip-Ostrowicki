package zl7;

public abstract class Product implements Sellable {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    abstract String getDescription();

    protected String getName() {
        return name;
    }

    protected String getCategory() {
        return category;
    }

    protected double getPrice() {
        return price;
    }
}
