package pd17.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import pd17.CategoryStatistics;
import pd17.sale.SaleService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public final class CsvWriter {
    ObjectMapper objectMapper = new ObjectMapper();

    private static List<CategoryStatistics> getStatistics(SaleService saleService) {
        Map<String, BigDecimal> revenueByCategory = saleService.getRevenueByCategory();
        Map<String, Double> averagePriceByCategory = saleService.getAveragePricePerCategory();

        return revenueByCategory.entrySet().stream()
                .map(entry -> new CategoryStatistics(entry.getKey(), entry.getValue(), averagePriceByCategory.get(entry.getKey())))
                .toList();
    }

    public void writeStatistics(SaleService saleService) throws IOException {
        List<CategoryStatistics> lines = getStatistics(saleService);
        Files.writeString(Path.of("src/main.pd17/output.csv"), "Kategoria;Łączny przychód;Średnia cena\n");

        lines.forEach(line -> {
            try {
                Files.writeString(Path.of("src/main.pd17/output.csv"), line.category() + ";" + line.totalRevenue() + ";" + line.averagePrice() + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("Wystąpił błąd podczas zapisu do pliku");
                throw new RuntimeException(e);
            }
        });
    }

    public void exportToJson(SaleService saleService) throws IOException {
        String summaryStatistics = objectMapper.writeValueAsString(getStatistics(saleService));

        Files.writeString(Path.of("src/main.pd17/output.json"), summaryStatistics);
    }
}