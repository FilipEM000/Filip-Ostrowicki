package pd16.Service;

import pd16.*;
import pd16.Exception.GameRegistrationException;
import pd16.Model.Client;
import pd16.Model.Game;
import pd16.Model.Rental;
import pd16.Repository.ClientRepository;
import pd16.Repository.GameRepository;
import pd16.Repository.RentalRepository;

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

        gameRepository.addGame(Game.of(name, category, price));
    }

    public void rentGame() {
        System.out.println("Podaj email użytkownika");
        String email = scanner.nextLine();
        Client client = clientRepository.findClient(email);
        System.out.println("Podaj nazwę gry, którą chciałby wypożyczyć");
        String gameName = scanner.nextLine();

        gameRepository.getGames().stream()
                .filter(game -> game.getName().equals(gameName))
                .filter(Game::isAvailable)
                .findFirst()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.RENTED);
                    rentalRepository.getRentals().add(new Rental(client, game));
                    System.out.println("Gra została pomyślnie wypożyczona");
                }, () -> System.err.printf("Gra %s nie jest dostępna\n", gameName));
    }

    public void returnGame() {
        System.out.println("Podaj nazwę gry, którą chciałby zwrócić");
        String gameName = scanner.nextLine();

        gameRepository.getGames().stream()
                .filter(game -> game.getName().equals(gameName))
                .filter(game -> game.getStatus() == Status.RENTED)
                .findFirst()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.AVAILABLE);
                    System.out.println("Gra została pomyślnie zwrócona");
                }, () -> System.err.println("Wystąpił błąd przy oddawaniu gry\n"));
    }

    public void printRentedGames() {
        gameRepository.getGames().stream()
                .filter(game -> game.getStatus() == Status.RENTED)
                .forEach(System.out::println);
    }
}
