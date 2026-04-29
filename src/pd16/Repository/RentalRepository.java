package pd16.Repository;

import lombok.Getter;
import pd16.Model.Rental;

import java.util.*;

@Getter
public class RentalRepository {
    private final List<Rental> rentals = new LinkedList<>();
}