package pd17;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor
public class Product {
    private final int productId;
    private final String productName;
    private final String category;
    private BigDecimal unitPrice;
}
