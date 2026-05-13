package pd17.sale;

import pd17.utils.CsvParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SaleRepository {
    private final Path path = Path.of("src/main.pd17/sales.csv");
    private final List<Sale> sales = CsvParser.loadSales(path);

    public List<Sale> getAll(){
        return sales;
    }

    public SaleRepository() throws IOException {
    }
}
