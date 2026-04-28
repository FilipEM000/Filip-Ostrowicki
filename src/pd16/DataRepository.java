package pd16;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DataRepository {
    List<Game> games = new ArrayList<>(List.of(
            Game.of("The Witcher 3", "RPG", new BigDecimal("149.99"), Status.AVAILABLE),
            Game.of("FIFA 26", "Sports", new BigDecimal("249.99"), Status.AVAILABLE),
            Game.of("Cyberpunk 2077", "RPG", new BigDecimal("199.99"), Status.AVAILABLE),
            Game.of("Minecraft", "Sandbox", new BigDecimal("99.99"), Status.AVAILABLE),
            Game.of("Call of Duty: Modern Warfare", "Shooter", new BigDecimal("299.99"), Status.AVAILABLE)));
    Set<Client> clients = new HashSet<>();
    List<Rental> rentals = new LinkedList<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void printGamesAndClients() {
        System.out.println("Gry:");
        System.out.println(games);
        System.out.println("Klienci:");
        System.out.println(clients);
    }

    public void rentGame(Client client, String gameName) {
        games.stream()
                .filter(game -> game.getName().equals(gameName))
                .filter(game -> game.getStatus() == Status.AVAILABLE)
                .findFirst()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.RENTED);
                    rentals.add(new Rental(client, game));
                    System.out.println("Gra została pomyślnie wypożyczona");
                }, () -> System.err.printf("Gra %s nie jest dostępna\n", gameName));
    }

    public void returnGame(Client client, String gameName) {
        games.stream()
                .filter(game -> game.getName().equals(gameName))
                .filter(game -> game.getStatus() == Status.RENTED)
                .findFirst()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.AVAILABLE);
                    System.out.println("Gra została pomyślnie zwrócona");
                }, () -> System.err.println("Wystąpił błąd przy oddawaniu gry\n"));
    }

    public void printRentedGames() {
        games.stream()
                .filter(game -> game.getStatus() == Status.RENTED)
                .forEach(System.out::println);
    }

    public void printAllGamesRentedByClient(Client client) {
        rentals.stream()
                .filter(rental -> rental.client().equals(client))
                .map(Rental::game)
                .forEach(System.out::println);
    }

    public void printReport() {
        System.out.println("Najczęściej wypożyczane gry:");
        rentals.stream()
                .collect(Collectors.groupingBy(Rental::game, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .forEach((Entry) -> System.out.println("Gra " + Entry.getKey().getName() + " została wypożyczona łącznie " + Entry.getValue() + " razy"));

        System.out.println("\nKlient z największą liczbą wypożyczeń: ");
        rentals.stream()
                .collect(Collectors.groupingBy(Rental::client, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent((Entry) -> System.out.println("Klient " + Entry.getKey().getName() + " wypożyczył grę aż " + Entry.getValue() + " razy"));

        System.out.println("\nPrzychód per kategoria");
        rentals.stream()
                .collect(Collectors.groupingBy(rental -> rental.game().getCategory(), Collectors.summingDouble(rental -> rental.game().getPrice().doubleValue())))
                .forEach((key, value) -> System.out.println(key + ": " + value + " PLN"));
    }
}
