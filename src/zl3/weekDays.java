package zl3;

import java.util.Scanner;

public class weekDays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę 1-7, która reprezentuje dzień tygodnia");
        int input = scanner.nextInt();
        String type = (input <= 5) ? "Dzień roboczy" : " Weekend";

        switch (input) {
            case 1 -> {
                System.out.println("Poniedziałek: " + type);
                System.out.println("Godziny odjazdu pierwszego autobusu: " + busHours(input));
            }
            case 2 -> {
                System.out.println("Wtorek: " + type);
                System.out.println("Godziny odjazdu pierwszego autobusu: " + busHours(input));
            }
            case 3 -> {
                System.out.println("Środa: " + type);
                System.out.println("Godziny odjazdu pierwszego autobusu: " + busHours(input));
            }
            case 4 -> {
                System.out.println("Czwartek: " + type);
                System.out.println("Godziny odjazdu pierwszego autobusu: " + busHours(input));
            }
            case 5 -> {
                System.out.println("Piątek: " + type);
                System.out.println("Godziny odjazdu pierwszego autobusu: " + busHours(input));
            }
            case 6 -> {
                System.out.println("Sobota+ " + type);
                System.out.println("Godziny odjazdu pierwszego autobusu: " + busHours(input));
            }
            case 7 -> {
                System.out.println("Niedziela: " + type);
                System.out.println("Godziny odjazdu pierwszego autobusu: " + busHours(input));
            }
            default -> {
                System.out.println("BŁĄD! Podaj liczbę z zakresu 1-7");
            }
        }
    }

    static String busHours(int input){
        if(input <= 5){
            return "05:30";
        } else if (input == 6) {
            return "07:00";
        } else {
            return "09:00";
        }
    }
}
