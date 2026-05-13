package pd13;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(Product.of(1, "1", "Elektronika", new BigDecimal(220), 3));
        warehouse.addProduct(Product.of(2, "2", "Elektronika", new BigDecimal(260), 3));
        warehouse.addProduct(Product.of(3, "3", "Elektronika", new BigDecimal(10), 6));
        warehouse.addProduct(Product.of(4, "4", "Elektronika", new BigDecimal(220), 3));
        warehouse.addProduct(Product.of(5, "5", "Elektronika", new BigDecimal(420), 3));
        warehouse.addProduct(Product.of(6, "6", "Kuchenne", new BigDecimal(220), 3));
        warehouse.addProduct(Product.of(7, "7", "Kuchenne", new BigDecimal(230), 3));
        warehouse.addProduct(Product.of(8, "8", "Kuchenne", new BigDecimal(12), 6));
        warehouse.addProduct(Product.of(9, "9", "Kuchenne", new BigDecimal(120), 3));
        warehouse.addProduct(Product.of(10, "10", "Kuchenne", new BigDecimal(420), 3));
        warehouse.addProduct(Product.of(11, "11", "Ogrodowe", new BigDecimal(320), 3));
        warehouse.addProduct(Product.of(12, "12", "Ogrodowe", new BigDecimal(560), 3));
        warehouse.addProduct(Product.of(13, "13", "Ogrodowe", new BigDecimal(10), 6));
        warehouse.addProduct(Product.of(14, "14", "Ogrodowe", new BigDecimal(40), 3));
        warehouse.addProduct(Product.of(15, "15", "Ogrodowe", new BigDecimal(20), 3));

        warehouse.removeProduct(Product.of(1, "1", "Elektronika", new BigDecimal(220), 3));
        System.out.println(warehouse.findByCategory("Ogrodowe"));
        System.out.println(warehouse.getCategoryStats());
        System.out.println(warehouse.getLowStockReport());
        System.out.println(warehouse.exportSortedByPrice());
    }
}
