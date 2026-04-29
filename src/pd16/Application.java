package pd16;

import pd16.Exception.ClientRegistrationException;
import pd16.Exception.GameRegistrationException;
import pd16.Repository.ClientRepository;
import pd16.Repository.GameRepository;
import pd16.Repository.RentalRepository;
import pd16.Service.ClientService;
import pd16.Service.GameService;
import pd16.Service.RentalService;

import java.util.Scanner;

public class Application {
    Scanner scanner = new Scanner(System.in);

    private final ClientService clientService;
    private final GameService gameService;
    private final RentalService rentalService;

    public Application() {
        ClientRepository clientRepository = new ClientRepository();
        RentalRepository rentalRepository = new RentalRepository();
        GameRepository gameRepository = new GameRepository();
        this.clientService = new ClientService(clientRepository);
        this.gameService = new GameService(gameRepository, rentalRepository, clientRepository);
        this.rentalService = new RentalService(rentalRepository, clientRepository);
    }

    public void startApplication() {
        int option;
        try {
            do {
                printMenu();
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 -> clientService.registerClient();
                    case 2 -> gameService.addNewGame();
                    case 3 -> gameService.rentGame();
                    case 4 -> gameService.returnGame();
                    case 5 -> gameService.printRentedGames();
                    case 6 -> rentalService.printAllGamesRentedByClient();
                    case 7 -> rentalService.printReport();
                    default -> System.out.println("Wybrana opcja nie istnieje");
                }
            } while (option != 0);
        } catch (ClientRegistrationException | GameRegistrationException e) {
            System.err.println(e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("""
                [1] - dodaj nowego użytkownika
                [2] - dodaj nową grę
                [3] - wypożycz grę
                [4] - zwróć grę
                [5] - wyświetl aktualnie wypożyczone gry
                [6] - wyświetl wszystkie wypożyczenia danego klienta
                [7] - wyświetl raport
                [0] - wyjdź z programu
                """);
    }
}