package pd17.utils;

import lombok.NoArgsConstructor;
import pd17.Product;
import pd17.sale.Sale;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public final class CsvParser {

    private static Optional<Sale> parseLine(String line, int lineNumber) {
        String[] parts = line.split(",", -1);
        if (parts.length < 6) {
            System.err.printf("W lini %d podano błędne dane", lineNumber);
            return Optional.empty();
        }

        return Optional.of(new Sale(new Product(Integer.parseInt(parts[1]), parts[2].trim(), parts[3].trim(), BigDecimal.valueOf(Double.parseDouble(parts[5]))),
                parts[0], Integer.parseInt(parts[4])));
    }

    public static List<Sale> loadSales(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<Sale> sales = new ArrayList<>();
        int[] lineNum = {0};

        for (String line : lines) {
            lineNum[0]++;
            if (lineNum[0] == 1 || line.isBlank()) continue;
            parseLine(line, lineNum[0]).ifPresent(sales::add);
        }
        return Collections.unmodifiableList(sales);
    }
}
