package pd03;

import java.util.Scanner;

public class homework03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        while (number == 0) {
            System.out.println("Podaj dowolną liczbę całkowitą inną niż 0");
            number = scanner.nextInt();
        }

        String type = number > 0 ? "positive" : "negative";
        String divisibility = divisibilityCheck(number);
        String size = sizeCheck(number);


        System.out.println("Number: " + number + ", Type: " + type + ", Divisibility: " + divisibility + ", Size: " + size);
    }

    private static String sizeCheck(int number) {
        if(Math.abs(number) <= 10){
            return "small";
        } else if (Math.abs(number) <= 100) {
            return "medium";
        }else {
            return "large;";
        }
    }


    private static String divisibilityCheck(int number){
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
