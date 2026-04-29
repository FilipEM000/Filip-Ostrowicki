package pd16.Model;

import lombok.*;
import pd16.Status;

import java.math.BigDecimal;

@Data
public class Game {
    private final String name;
    private final String category;
    private BigDecimal price;
    private Status status;

    public boolean isAvailable(){
        return status == Status.AVAILABLE;
    }

    private Game(String name, String category, BigDecimal price){
        this.name = name;
        this.category = category;
        this.price = price;
        this.status = Status.AVAILABLE;
    }

    public static Game of(String name, String category, BigDecimal price){
        return new Game(name, category, price);
    }
}
