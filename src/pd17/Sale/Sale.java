package pd17.Sale;

import pd17.Product;

public record Sale(Product product, String date, int quantity) {
}
