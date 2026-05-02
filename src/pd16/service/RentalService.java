package pd16.service;

import pd16.model.Client;
import pd16.model.Rental;
import pd16.repository.ClientRepository;
import pd16.repository.RentalRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
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
        rentalRepository.findAll().stream()
                .collect(Collectors.groupingBy(Rental::game, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .forEach((Entry) -> System.out.println("Gra " + Entry.getKey().getName() + " została wypożyczona łącznie " + Entry.getValue() + " razy"));

        System.out.println("\nKlient z największą liczbą wypożyczeń: ");
        rentalRepository.findAll().stream()
                .collect(Collectors.groupingBy(Rental::client, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent((Entry) -> System.out.println("Klient " + Entry.getKey().getName() + " wypożyczył grę aż " + Entry.getValue() + " razy"));

        System.out.println("\nPrzychód per kategoria");
        rentalRepository.findAll().stream()
                .collect(Collectors.groupingBy(rental -> rental.game().getCategory(), Collectors.mapping(rental -> rental.game().getPrice(), Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))))
                .forEach((key, value) -> System.out.println(key + ": " + value + " PLN"));
    }

    public void printAllGamesRentedByClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj email użytkownika");
        String email = scanner.nextLine();
        Client client = clientRepository.findClient(email);

        rentalRepository.findAll().stream()
                .filter(rental -> rental.client().equals(client))
                .map(Rental::game)
                .forEach(System.out::println);
    }
}