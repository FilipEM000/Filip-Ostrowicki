package pd17;

import pd17.Sale.SaleService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class CsvWriter {
    public static void writeStatistics(SaleService saleService) throws IOException {
        Map<String, BigDecimal> revenueByCategory = saleService.getRevenueByCategory();
        Map<String, Double> averagePriceByCategory = saleService.getAveragePricePerCategory();

        List<String> lines = revenueByCategory.entrySet().stream()
                .map(entry -> entry.getKey() + ";" + entry.getValue() + ";" + averagePriceByCategory.get(entry.getKey()))
                .toList();

        Files.write(Path.of("src/pd17/output.csv"), lines);
    }
}
