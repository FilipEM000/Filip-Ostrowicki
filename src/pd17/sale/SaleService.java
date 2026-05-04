package pd17.sale;

import pd17.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleService {
    SaleRepository saleRepository = new SaleRepository();

    public SaleService() throws IOException {
    }

    public BigDecimal getTotalRevenue() {
        return saleRepository.getAll().stream()
                .map(sale -> sale.product().getUnitPrice().multiply(BigDecimal.valueOf(sale.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Product> getTop3Products() {
        return saleRepository.getAll().stream()
                .sorted(Comparator.comparing(sale -> sale.product().getUnitPrice().multiply(BigDecimal.valueOf(sale.quantity())), Comparator.reverseOrder()))
                .limit(3)
                .map(Sale::product)
                .toList();
    }

    public Map<String, BigDecimal> getRevenueByCategory() {
        return saleRepository.getAll().stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.product().getCategory(),
                        Collectors.mapping(sale -> sale.product().getUnitPrice().multiply(BigDecimal.valueOf(sale.quantity())), Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));
    }

    public Map<String, Double> getAveragePricePerCategory() {
        return saleRepository.getAll().stream()
                .map(Sale::product)
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(product -> product.getUnitPrice().doubleValue())
                ));
    }
}
