package pd15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor(staticName = "of")
public class Product {
    private final int id;
    private final String name;
    private final String category;
    private BigDecimal price;
    private int stock;
    private int sold;
    private double rating;
}
