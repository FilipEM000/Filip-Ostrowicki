package zl3;

import java.util.Scanner;

public class weekDays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę 1-7, która reprezentuje dzień tygodnia");
        int input = scanner.nextInt();
        String type = (input <= 5) ? "Dzień roboczy" : " Weekend";

        switch (input) {
            case 1 ->
                    System.out.printf("Poniedziałek: %s \nGodziny odjazdu pierwszego autobusu: %s", type, busHours(1));
            case 2 -> System.out.printf("Wtorek: %s \nGodziny odjazdu pierwszego autobusu: %s", type, busHours(2));
            case 3 -> System.out.printf("Środa: %s \nGodziny odjazdu pierwszego autobusu: %s", type, busHours(3));
            case 4 -> System.out.printf("Czwartek: %s \nGodziny odjazdu pierwszego autobusu: %s", type, busHours(4));
            case 5 -> System.out.printf("Piątek: %s \nGodziny odjazdu pierwszego autobusu: %s", type, busHours(5));
            case 6 -> System.out.printf("Sobota: %s \nGodziny odjazdu pierwszego autobusu: %s", type, busHours(6));
            case 7 -> System.out.printf("Niedziela: %s \nGodziny odjazdu pierwszego autobusu: %s", type, busHours(7));
            default -> throw new RuntimeException("Podaj liczbę z zakresu 1-7");

        }
    }

    static String busHours(int input) {
        switch (input) {
            case 1, 2, 3, 4, 5 -> {
                return "05:30";
            }
            case 6 -> {
                return "07:00";
            }
            default -> {
                return "09:00";
            }
        }
    }
}
