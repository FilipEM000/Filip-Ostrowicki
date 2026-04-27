package pd16;

import java.math.BigDecimal;
import java.util.Scanner;

public class Application {
    static DataRepository database = new DataRepository();
    static Scanner scanner = new Scanner(System.in);

    public static void startApplication() {
        int option;
        try {
            do {
                printMenu();
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 -> registerUser();
                    case 2 -> addNewGame();
                    case 3 -> showAllGamesAndClients();
                    case 4 -> rentGame();
                    case 5 -> returnGame();
                    case 6 -> showAllRentedGames();
                    case 7 -> showAllRentalsOfClient();
                    case 8 -> printReport();
                    default -> System.out.println("Wybrana opcja nie istnieje");
                }
            } while (option != 0);
        } catch (ClientRegistrationException | GameRegistrationException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("""
                [1] - dodaj nowego użytkownika
                [2] - dodaj nową grę
                [3] - wyświetl wszystkich użytkowników i gry
                [4] - wypożycz grę
                [5] - zwróć grę
                [6] - wyświetl aktualnie wypożyczone gry
                [7] - wyświetl wszystkie wypożyczenia danego klienta
                [8] - wyświetl raport
                [0] - wyjdź z programu
                """);
    }

    private static void registerUser() {
        System.out.println("Podaj nazwę użytkownika");
        String name = scanner.nextLine();
        System.out.println("Podaj email użytkownika");
        String email = scanner.nextLine();
        if (name.isEmpty() || email.isEmpty()) {
            throw new ClientRegistrationException("Klient nie może miec pustej nazwy ani emaila");
        }
        for (Client client : database.clients) {
            if (client.getEmail().equals(email)) {
                throw new ClientRegistrationException("Klient z podanym emailem już istnieje");
            }
        }
        database.addClient(Client.of(name, email));
    }

    private static void addNewGame() {
        System.out.println("Podaj nazwę gry");
        String name = scanner.nextLine();
        System.out.println("Podaj kategorię gry");
        String category = scanner.nextLine();
        System.out.println("Podaj cenę gry");
        BigDecimal price = new BigDecimal(scanner.nextInt());
        if (name.isEmpty() || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new GameRegistrationException("Gra nie może mieć pustej nazwy ani ceny mniejszej równej 0");
        }
        database.addGame(Game.of(name, category, price, Status.AVAILABLE));
    }

    private static void showAllGamesAndClients() {
        database.printGamesAndClients();
    }

    private static Client findClient() {
        System.out.println("Podaj email użytkownika");
        String email = scanner.nextLine();
        return database.clients.stream()
                .filter(client1 -> client1.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new ClientRegistrationException("Klient z podanym emailem nie istnieje"));
    }

    private static void rentGame() {
        Client client = findClient();

        System.out.println("Podaj nazwę gry, którą chciałby wypożyczyć");
        String gameName = scanner.nextLine();
        database.rentGame(client, gameName);
    }

    private static void returnGame() {
        Client client = findClient();

        System.out.println("Podaj nazwę gry, którą chciałby zwrócić");
        String gameName = scanner.nextLine();
        database.returnGame(client, gameName);
    }

    private static void showAllRentedGames() {
        database.printRentedGames();
    }

    private static void showAllRentalsOfClient() {
        Client client = findClient();
        database.printAllGamesRentedByClient(client);
    }

    private static void printReport() {
        database.printReport();
    }
}
