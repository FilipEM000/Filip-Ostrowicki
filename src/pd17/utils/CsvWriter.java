package pd17.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import pd17.sale.SaleService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public final class CsvWriter {
    private static List<String> getStatistics(SaleService saleService) {
        Map<String, BigDecimal> revenueByCategory = saleService.getRevenueByCategory();
        Map<String, Double> averagePriceByCategory = saleService.getAveragePricePerCategory();

        return revenueByCategory.entrySet().stream()
                .map(entry -> entry.getKey() + ";" + entry.getValue() + ";" + averagePriceByCategory.get(entry.getKey()))
                .toList();
    }

    public static void writeStatistics(SaleService saleService) throws IOException {
        List<String> lines = getStatistics(saleService);

        Files.write(Path.of("src/pd17/output.csv"), lines);
    }

    public static void exportToJson(SaleService saleService) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> lines = getStatistics(saleService);

        Files.write(Path.of("src/pd17/output.json"), objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lines).getBytes());
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lines));
    }
}
