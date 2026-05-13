package pd15;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductUtils {
    public static void printTopFiveProducts(List<Product> products) {
        products.stream()
                .sorted(Comparator.comparing(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getSold()))))
                .limit(5)
                .forEach(System.out::println);
    }

    public static void printCategoryWithHighestRating(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getRating)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
    }

    public static void printProductsWithLowStock(List<Product> products) {
        products.stream()
                .filter(product -> product.getStock() < 10)
                .sorted(Comparator.comparing(Product::getStock))
                .forEach(System.out::println);
    }

    public static void printNumberOfProductsPerCategory(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()))
                .forEach((k, v) -> System.out.println("Kategoria: " + k + ", liczba produktów: " + v));
    }

    public static void printTotalIncomeAndAveragePricePerCategory(List<Product> products) {
        Map<String, Double> income = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(product -> product.getPrice().doubleValue() * product.getSold())));

        Map<String, Double> averagePrice = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(product -> product.getPrice().doubleValue())));

        income.forEach((k, v) -> System.out.println("Kategoria: " + k + ", całkowity przychód: " + v + ", średnia cena: " + averagePrice.get(k)));
    }

    public static void printProductsByRatingAndPrice(List<Product> products) {
        products.stream()
                .filter(product -> product.getRating() >= 4.5)
                .filter(product -> product.getPrice().compareTo(new BigDecimal(500)) < 0)
                .sorted(Comparator.comparing(Product::getPrice))
                .forEach(System.out::println);
    }

    public static boolean isEveryProductRatingOver0(List<Product> products) {
        return products.stream()
                .allMatch(product -> product.getRating() > 0);
    }

    public static Optional<Product> findProductWithHighestPrice(List<Product> products) {
        return products.stream()
                .max(Comparator.comparing(Product::getPrice));
    }

    public static void printNumberOfProductsByPriceTier(List<Product> products) {
        List<Product> premium = products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal(1000)) > 0)
                .toList();

        System.out.println("Liczba produktów premium: " + premium.size() + ", Liczba produktów standardowych: " + (products.size() - premium.size()));
    }

    public static void printReport(List<Product> products) {
        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.maxBy(Comparator.comparing(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getSold()))))))
                .forEach((k, product) -> System.out.println("Kategoria: " + k + " , top product: " + product));
    }
}
