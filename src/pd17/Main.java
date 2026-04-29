package pd17;

import pd17.Sale.SaleService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SaleService saleService = new SaleService();

        CsvWriter.writeStatistics(saleService);
    }
}
