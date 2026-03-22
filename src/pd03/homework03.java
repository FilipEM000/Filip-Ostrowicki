package pd03;

import java.util.Scanner;

public class homework03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            System.out.println("Podaj dowolną liczbę całkowitą inną niż 0");
            number = scanner.nextInt();
        } while (number == 0);

        String type = number > 0 ? "positive" : "negative";
        String divisibility = checkDivisibility(number);
        String size = checkSize(number);


        System.out.println("Number: " + number + ", Type: " + type + ", Divisibility: " + divisibility + ", Size: " + size);
    }

    private static String checkSize(int number) {
        if (Math.abs(number) <= 10) {
            return "small";
        } else if (Math.abs(number) <= 100) {
            return "medium";
        } else {
            return "large";
        }
    }


    private static String checkDivisibility(int number) {
        if (number % 6 == 0) {
            return "divisible by 2 and 3";
        } else if (number % 2 == 0) {
            return "divisible by 2";
        } else if (number % 3 == 0) {
            return "divisible by 3";
        } else {
            return "not divisible by 2 or 3";
        }
    }


}
