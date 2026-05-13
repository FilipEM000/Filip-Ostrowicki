package pd13;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    Map<Long, Product> inventory = new HashMap<>();
    Map<String, Set<Product>> byCategory = new HashMap<>();
    PriorityQueue<Product> lowStock = new PriorityQueue<>(Comparator.comparing(Product::getQuantity).thenComparing(Product::compareTo));

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);
        byCategory.computeIfAbsent(product.getCategory(), k -> new HashSet<>()).add(product);
        if (product.getQuantity() < 5) {
            lowStock.offer(product);
        }
    }

    public void removeProduct(Product product) {
        inventory.remove(product.getId());
        byCategory.get(product.getCategory()).remove(product);
        lowStock.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        product.setQuantity(quantity);

        lowStock.remove(product);
        if (quantity < 5) {
            lowStock.offer(product);
        }
    }

    public List<Product> findByCategory(String category) {
        if (byCategory.get(category) == null) {
            return Collections.emptyList();
        }

        return byCategory.get(category).stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    public List<Product> getLowStockReport() {
        return lowStock.stream()
                .sorted(Comparator.comparing(Product::getQuantity))
                .toList();
    }

    public Map<String, DoubleSummaryStatistics> getCategoryStats() {
        return inventory.values().stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summarizingDouble(p -> p.getPrice().doubleValue())
                ));
    }

    public TreeMap<BigDecimal, List<Product>> exportSortedByPrice() {
        return inventory.values().stream()
                .collect(Collectors.groupingBy(
                        Product::getPrice,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }
}