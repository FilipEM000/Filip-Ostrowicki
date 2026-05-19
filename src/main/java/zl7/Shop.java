package zl7;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        Product telefon = new Electronics("nazwa", 999.99, "Smartfon", 12);
        Product komputer = new Electronics("PC", 1999.99, "laptop", 23);
        Product mleko = new FoodProduct("mleko", 6.99, "nabiał", 1);
        Product chleb = new FoodProduct("chleb", 3.99, "pieczywo", 1);
        Product telefon2 = new Electronics("nazwa", 999.99, "Smartfon", 12);

        products.add(telefon);
        products.add(komputer);
        products.add(mleko);
        products.add(chleb);
        products.add(telefon2);

        int countOfElectronics = 0;
        for (Product product : products) {
            System.out.println(product.getDescription());
            if (product instanceof Electronics) {
                countOfElectronics++;
            }
        }
        System.out.println("Liczba elektroniki: " + countOfElectronics);
    }
}
