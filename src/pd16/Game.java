package pd16;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(staticName = "of")
public class Game {
    private final String name;
    private final String category;
    private BigDecimal price;
    private Status status;
}
