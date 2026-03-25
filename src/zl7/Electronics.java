package zl7;

public class Electronics extends Product {
    private static double VAT = 1.05;
    private int warranty;

    public Electronics(String name, double price, String category, int warranty) {
        super(name, price, category);
        this.warranty = warranty;
    }

    public String getDescription() {
        return "nazwa: " + getName() + " opis: " + getCategory();
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() * VAT;
    }
}
