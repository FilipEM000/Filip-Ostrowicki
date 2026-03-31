package pd07;

import java.math.BigDecimal;
import java.util.*;

public class RentalSystem {
    public static void main(String[] args) {
        List<Rental> rentals = new ArrayList<>();
        //początkowa baza wypożyczeń
        rentals.add(new Rental(Laptop.of("A1", "Lenovo", new BigDecimal("19.99"), 15.6), 60, Status.ACTIVE));
        rentals.add(new Rental(Laptop.of("A2", "ASUS", new BigDecimal("39.99"), 15.6), 60, Status.LATE));
        rentals.add(new Rental(Console.of("B1", "XBOX", new BigDecimal("29.99"), "ONE-S"), 60, Status.ACTIVE));
        rentals.add(new Rental(Console.of("B2", "PS", new BigDecimal("49.99"), "5-PRO"), 60, Status.RETURNED));
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();
            rentals.sort(Comparator.comparing(Rental::getResource).thenComparing(Rental::getResourceName));
            try {
                switch (option) {
                    case 1 -> addRental(rentals);
                    case 2 -> getTotalCost(rentals);
                    case 3 -> getNumberOfRentalsByStatus(rentals);
                    case 4 -> printAllRentals(rentals);
                    case 0 -> System.out.println("Koniec programu");
                    default -> System.out.println("Nie podałeś poprawnej opcji");
                }
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.err.println("Podałeś złą wartość!");
            }
        } while (option != 0);
    }

    private static void printAllRentals(List<Rental> rentals) {
        for (Rental r : rentals) {
            System.out.println(r);
        }
        System.out.println("-----");
    }

    private static void getNumberOfRentalsByStatus(List<Rental> rentals) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jaki status cię interesuje?");
        String input = scanner.nextLine();
        Status status = parseStatusFromString(input);

        int result = 0;
        for (Rental rental : rentals) {
            if (rental.getStatus() == status) {
                result++;
            }
        }
        System.out.println("Liczba wypożyczeń o statusie " + status + ": " + result);
    }

    private static void menu() {
        System.out.println("""
                [1] - dodaj nowe wypożyczenie
                [2] - oblicz łączny koszt wszystich wypożyczeń
                [3] - wyświetl liczbę wypożyczeń o wskazanym stanie
                [4] - wyświetl wszystkie wypożyczenia
                [0] - wyjdź z programu
                """);
    }

    private static void addRental(List<Rental> rentals) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jaki produkt został wypożyczony?\nPodaj numer id:");
        String id = scanner.nextLine();
        System.out.println("Podaj nazwę");
        String name = scanner.nextLine();
        System.out.println("Podaj cenę bazową");
        String priceInput = scanner.nextLine();
        BigDecimal price = new BigDecimal(priceInput);
        System.out.println("Podaj typ wypożyczonego przedmiotu");
        String input = scanner.nextLine();
        Type type = parseTypeFromString(input);
        System.out.println("Na ile dni jest wypożyczenie?");
        int numberOfDays = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case LAPTOP -> {
                System.out.println("Podaj ile cali ma laptop");
                double inch = scanner.nextDouble();
                scanner.nextLine();
                rentals.add(new Rental(new Laptop(id, name, price, type, inch), numberOfDays, Status.ACTIVE));
            }
            case CONSOLE -> {
                System.out.println("Podaj jaki to model konsoli");
                String model = scanner.nextLine();
                rentals.add(new Rental(new Console(id, name, price, type, model), numberOfDays, Status.ACTIVE));
            }
        }
        System.out.println("Udało się dodać wypożyczenie!");
    }

    private static void getTotalCost(List<Rental> rentals) {
        BigDecimal sum = new BigDecimal(0);
        for (Rental rental : rentals) {
            sum = sum.add(rental.calculateTotalPrice());
        }
        System.out.println("Łączna koszt wszystkich wypożyczeń to: " + sum);
    }

    private static Status parseStatusFromString(String input) {
        return Arrays.stream(Status.values())
                .filter(s -> s.name().equals(input.strip().toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static Type parseTypeFromString(String input) {
        return Arrays.stream(Type.values())
                .filter(s -> s.name().equals(input.strip().toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
