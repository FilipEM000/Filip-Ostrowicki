package pd16;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class RentalService {
    RentalRepository rentalRepository;
    ClientRepository clientRepository;

    public RentalService(RentalRepository rentalRepository, ClientRepository clientRepository) {
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
    }

    public void printReport() {
        System.out.println("Najczęściej wypożyczane gry:");
        rentalRepository.rentals.stream()
                .collect(Collectors.groupingBy(Rental::game, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .forEach((Entry) -> System.out.println("Gra " + Entry.getKey().getName() + " została wypożyczona łącznie " + Entry.getValue() + " razy"));

        System.out.println("\nKlient z największą liczbą wypożyczeń: ");
        rentalRepository.rentals.stream()
                .collect(Collectors.groupingBy(Rental::client, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent((Entry) -> System.out.println("Klient " + Entry.getKey().getName() + " wypożyczył grę aż " + Entry.getValue() + " razy"));

        System.out.println("\nPrzychód per kategoria");
        rentalRepository.rentals.stream()
                .collect(Collectors.groupingBy(rental -> rental.game().getCategory(), Collectors.summingDouble(rental -> rental.game().getPrice().doubleValue())))
                .forEach((key, value) -> System.out.println(key + ": " + value + " PLN"));
    }

    public void printAllGamesRentedByClient() {
        Client client = clientRepository.findClient();

        rentalRepository.rentals.stream()
                .filter(rental -> rental.client().equals(client))
                .map(Rental::game)
                .forEach(System.out::println);
    }
}
