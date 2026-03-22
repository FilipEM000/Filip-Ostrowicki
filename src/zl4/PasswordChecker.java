package zl4;

import java.util.Scanner;

public class PasswordChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String correctPassword = "java123";
        int triesNumber = 0;
        String password;

        System.out.println("Podaj hasło");
        while (triesNumber < 3) {
            password = scanner.nextLine();
            if (password.length() < 3) {
                System.out.println("Password too short");
                continue;
            }

            if (password.equals(correctPassword)) {
                System.out.println("Login successful");
                return;
            }

            triesNumber++;
            System.out.println("Wrong password");
        }

        System.out.println("Account locked");
    }
}
