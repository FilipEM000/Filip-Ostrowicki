package pd17.Sale;

import pd17.CsvParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SaleRepository {
    private final Path path = Path.of("src/pd17/sales.csv");

    List<Sale> sales = CsvParser.loadSales(path);

    public SaleRepository() throws IOException {
    }
}
