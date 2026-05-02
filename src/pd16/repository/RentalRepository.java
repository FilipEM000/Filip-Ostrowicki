package pd16.repository;

import pd16.model.Rental;

import java.util.*;

public class RentalRepository {
    private List<Rental> rentals = new ArrayList<>();

    public List<Rental> findAll() {
        return rentals;
    }

    public void save(Rental rental) {
        rentals.add(rental);
    }
}