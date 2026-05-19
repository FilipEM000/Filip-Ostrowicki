package pd15;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {
    List<Product> productList = new ArrayList<>(List.of(
            Product.of(1, "Laptop Dell XPS 13", "Electronics", new BigDecimal("5999.99"), 9, 50, 4.7),
            Product.of(2, "Smartphone Samsung Galaxy S23", "Electronics", new BigDecimal("3999.99"), 25, 120, 4.6),
            Product.of(3, "Headphones Sony WH-1000XM5", "Electronics", new BigDecimal("1499.99"), 20, 80, 4.8),
            Product.of(4, "Smartwatch Apple Watch Series 9", "Electronics", new BigDecimal("2499.99"), 20, 95, 4.5),
            Product.of(5, "Vacuum Cleaner Dyson V15", "Home Appliances", new BigDecimal("2999.99"), 8, 40, 4.7),
            Product.of(6, "Air Fryer Philips XXL", "Home Appliances", new BigDecimal("899.99"), 30, 150, 4.6),
            Product.of(7, "Coffee Machine DeLonghi", "Home Appliances", new BigDecimal("1299.99"), 12, 60, 4.4),
            Product.of(8, "Blender Bosch Pro", "Home Appliances", new BigDecimal("499.99"), 18, 70, 4.3),
            Product.of(9, "Clean Code", "Books", new BigDecimal("119.99"), 50, 300, 4.9),
            Product.of(10, "Effective Java", "Books", new BigDecimal("139.99"), 5, 250, 4.8),
            Product.of(11, "Design Patterns", "Books", new BigDecimal("159.99"), 35, 200, 4.7),
            Product.of(12, "Refactoring", "Books", new BigDecimal("129.99"), 45, 220, 4.6),
            Product.of(13, "Nike Air Max Shoes", "Clothing", new BigDecimal("499.99"), 60, 300, 4.5),
            Product.of(14, "Adidas Hoodie", "Clothing", new BigDecimal("299.99"), 70, 280, 4.4),
            Product.of(15, "Levi's Jeans", "Clothing", new BigDecimal("399.99"), 55, 260, 4.6),
            Product.of(16, "Puma T-Shirt", "Clothing", new BigDecimal("149.99"), 3, 310, 4.3),
            Product.of(17, "Organic Honey", "Food", new BigDecimal("39.99"), 100, 500, 4.9),
            Product.of(18, "Italian Olive Oil", "Food", new BigDecimal("59.99"), 90, 450, 4.8),
            Product.of(19, "Dark Chocolate 85%", "Food", new BigDecimal("19.99"), 120, 600, 4.7),
            Product.of(20, "Green Tea Premium", "Food", new BigDecimal("29.99"), 110, 550, 4.6)
    ));

    public List<Product> getProductList(){
        return Collections.unmodifiableList(productList);
    }
}
