package zl11;

import java.util.ArrayList;
import java.util.List;

public class LaptopStore {
    private int availableQuantity = 10;

    List<Order> orders = new ArrayList<>(List.of(
            new Order("name", 4),
            new Order("name2", 3),
            new Order("name3", 7)
    ));

    public synchronized OrderStats buyLaptop(String name, int amount) {
        if (amount > availableQuantity) {
            return new OrderStats(name, 0, false, "Nie udało się");
        } else {
            availableQuantity -= amount;
            return new OrderStats(name, amount, true, "Udało się");
        }
    }
}
