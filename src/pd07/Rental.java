package pd07;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Rental {
    private final Resource resource;
    private int numberOfDays;
    private Status status;

    public Rental(Resource resource, int numberOfDays, Status status) {
        this.resource = resource;
        this.numberOfDays = numberOfDays;
        this.status = status;
    }

    public String getResourceName() {
        return resource.getName();
    }

    public BigDecimal calculateTotalPrice(){
        return resource.calculatePricePerDay().multiply(BigDecimal.valueOf(numberOfDays));
    }
}
