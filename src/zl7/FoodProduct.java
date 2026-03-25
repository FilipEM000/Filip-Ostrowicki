package zl7;

public class FoodProduct extends Product {
    private static double VAT = 1.23;
    private int expiryDate;

    public FoodProduct(String name, double price, String category, int expiryDate) {
        super(name, price, category);
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return "nazwa: " + getName() + " opis: " + getCategory();
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() * VAT;
    }
}
