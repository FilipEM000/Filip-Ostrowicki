package pd07;

import java.util.*;

public class RentalSystem {
    public static void main(String[] args) {
        List<Rental> rentals = new ArrayList<>();
        //początkowa baza wypożyczeń
        rentals.add(new Rental(new Laptop("A1", "Lenovo", 59.99, Type.LAPTOP, 15.6), 60, Status.ACTIVE));
        rentals.add(new Rental(new Laptop("A2", "ASUS", 99.99, Type.LAPTOP, 15.6), 60, Status.LATE));
        rentals.add(new Rental(new Console("B1", "Lenovo", 49.99, Type.CONSOLE, "XBOX"), 60, Status.ACTIVE));
        rentals.add(new Rental(new Console("B2", "ASUS", 89.99, Type.CONSOLE, "PS"), 60, Status.RETURNED));
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();
            rentals.sort(Comparator.comparing((Rental r) -> r.resource().getName())
                    .thenComparing(Rental::resource));
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
        Status status = Status.valueOf(scanner.nextLine());
        int result = 0;
        for (Rental rental : rentals) {
            if (rental.getStatus() == status) {
                result++;
            }
        }
        System.out.println("Liczba wyppżyczeń o statusie " + status + ": " + result);
    }

    private static void menu() {
        System.out.println("""
                [1] - dodaj nowe wypożyczenie
                [2] - oblicz łączny koszt wszystich wypożyczeń
                [3] - wyświetl liczbę wypożyczeń o wskazanym stanie
                [4] - wyświetl wszystkiw wypożyczenia
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
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Podaj typ wypożyczonego przedmiotu");
        Type type = Type.valueOf(scanner.nextLine());
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
        double sum = 0;
        for (Rental rental : rentals) {
            sum = sum + rental.getRentalCost();
        }
        System.out.println("Łączna koszt wszystkich wypożyczeń to: " + sum);
    }
}
