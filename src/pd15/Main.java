package pd15;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductRepository database = new ProductRepository();
        List<Product> products = database.getProductList();

        ProductUtils.printTopFiveProducts(products);
        System.out.println("--------");
        ProductUtils.printCategoryWithHighestRating(products);
        System.out.println("--------");
        ProductUtils.printProductsWithLowStock(products);
        System.out.println("--------");
        ProductUtils.printNumberOfProductsPerCategory(products);
        System.out.println("--------");
        ProductUtils.printTotalIncomeAndAveragePricePerCategory(products);
        System.out.println("--------");
        ProductUtils.printTotalIncomeAndAveragePricePerCategory(products);
        System.out.println("--------");
        ProductUtils.printProductsByRatingAndPrice(products);
        System.out.println("--------");
        System.out.println("Czy wszystkie produkty mają ocenę ponad 0: " + ProductUtils.isEveryProductRatingOver0(products));
        System.out.println("--------");
        System.out.println("Produkt o najwyższej cenie: " + ProductUtils.findProductWithHighestPrice(products));
        System.out.println("--------");
        ProductUtils.printNumberOfProductsByPriceTier(products);
        System.out.println("--------");
        ProductUtils.printReport(products);
    }
}
