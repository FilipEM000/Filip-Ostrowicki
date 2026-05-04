package pd17.sale;

import pd17.Product;

public record Sale(Product product, String date, int quantity) {
}
