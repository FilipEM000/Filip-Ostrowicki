package pd16.service;

import pd16.exception.GameRegistrationException;
import pd16.model.Client;
import pd16.model.Game;
import pd16.model.Rental;
import pd16.model.Status;
import pd16.repository.ClientRepository;
import pd16.repository.GameRepository;
import pd16.repository.RentalRepository;

import java.math.BigDecimal;
import java.util.Scanner;

public class GameService {
    Scanner scanner = new Scanner(System.in);
    GameRepository gameRepository;
    ClientRepository clientRepository;
    RentalRepository rentalRepository;

    public GameService(GameRepository gameRepository, RentalRepository rentalRepository, ClientRepository clientRepository) {
        this.gameRepository = gameRepository;
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
    }

    public void addNewGame() {
        System.out.println("Podaj nazwę gry");
        String name = scanner.nextLine();
        System.out.println("Podaj kategorię gry");
        String category = scanner.nextLine();
        System.out.println("Podaj cenę gry");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        if (name.isEmpty()) {
            throw new GameRegistrationException("Gra nie może mieć pustej nazwy");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GameRegistrationException("Gra nie może mieć ceny mniejszej niż 0");
        }

        gameRepository.save(Game.of(name, category, price));
    }

    public void rentGame() {
        System.out.println("Podaj email użytkownika");
        String email = scanner.nextLine();
        Client client = clientRepository.findClient(email);
        System.out.println("Podaj nazwę gry, którą chciałby wypożyczyć");
        String gameName = scanner.nextLine();

        gameRepository.findAll().stream()
                .filter(game -> game.getName().equals(gameName))
                .filter(Game::isAvailable)
                .findFirst()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.RENTED);
                    rentalRepository.save(new Rental(client, game));
                    System.out.println("Gra została pomyślnie wypożyczona");
                }, () -> System.err.printf("Gra %s nie jest dostępna\n", gameName));
    }

    public void returnGame() {
        System.out.println("Podaj nazwę gry, którą chciałby zwrócić");
        String gameName = scanner.nextLine();

        gameRepository.findAll().stream()
                .filter(game -> game.getName().equals(gameName))
                .filter(game -> game.getStatus() == Status.RENTED)
                .findFirst()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.AVAILABLE);
                    System.out.println("Gra została pomyślnie zwrócona");
                }, () -> System.err.println("Wystąpił błąd przy oddawaniu gry\n"));
    }

    public void printRentedGames() {
        gameRepository.findAll().stream()
                .filter(game -> game.getStatus() == Status.RENTED)
                .forEach(System.out::println);
    }
}