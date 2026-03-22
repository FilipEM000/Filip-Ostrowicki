package pd04;

import java.util.Scanner;

public class homework04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Wczytanie liczby graczy i wyników
        System.out.println("Podaj liczbę graczy");
        int numberOfPlayers = scanner.nextInt();
        scanner.nextLine();

        String[] names = new String[numberOfPlayers];
        int[][] scores = new int[numberOfPlayers][3];

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Podaj imię gracza numer " + (i + 1));
            names[i] = scanner.nextLine();
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0 -> System.out.println("Podaj pierwszy wynik gracza " + names[i]);
                    case 1 -> System.out.println("Podaj drugi wynik gracza " + names[i]);
                    case 2 -> System.out.println("Podaj trzeci wynik gracza " + names[i]);
                }

                scores[i][j] = scanner.nextInt();
            }
            scanner.nextLine();
        }

        //Obliczenia
        int[] sums = new int[numberOfPlayers];
        double[] averages = new double[numberOfPlayers];
        int[] mins = new int[numberOfPlayers];
        int[] maxes = new int[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            int suma = 0;
            int max = scores[i][0];
            int min = scores[i][0];
            for (int j = 0; j < scores[i].length; j++) {
                suma = suma + scores[i][j];
                if (scores[i][j] > max) {
                    max = scores[i][j];
                }
                if (scores[i][j] < min) {
                    min = scores[i][j];
                }
            }
            sums[i] = suma;
            averages[i] = (double) suma / 3;
            mins[i] = min;
            maxes[i] = max;
        }

        //sortowanie
        for (int i = 0; i < numberOfPlayers - 1; i++) {
            for (int j = 0; j < numberOfPlayers - 1 - i; j++) {
                if (sums[j] < sums[j + 1]) {
                    int tempS = sums[j];
                    sums[j] = sums[j + 1];
                    sums[j + 1] = tempS;

                    double tempA = averages[j];
                    averages[j] = averages[j + 1];
                    averages[j + 1] = tempA;

                    int tempMin = mins[j];
                    mins[j] = mins[j + 1];
                    mins[j + 1] = tempMin;

                    int tempMax = maxes[j];
                    maxes[j] = maxes[j + 1];
                    maxes[j + 1] = tempMax;

                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }

        int bestScore = maxes[0];
        for (int max : maxes) {
            if (max > bestScore)
                bestScore = max;
        }


        //Leaderboard
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print(names[i] + " - " + sums[i]);
            if (maxes[i] == bestScore) {
                System.out.println(" *");
            } else {
                System.out.println();
            }
        }

        //Podium
        System.out.print("Podium: ");
        for (int i = 0; i < Math.min(3, numberOfPlayers); i++) {
            System.out.print(names[i] + ", ");
        }

    }
}
