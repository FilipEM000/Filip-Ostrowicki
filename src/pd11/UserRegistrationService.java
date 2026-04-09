package pd11;

import java.util.Scanner;

public class UserRegistrationService {

    public static void main(String[] args) {
        UserDatabase database = new UserDatabase();
        Scanner scanner = new Scanner(System.in);

        int option;
        do {
            try {
                System.out.println("Podaj nazwe");
                String name = scanner.nextLine();
                System.out.println("Podaj email");
                String email = scanner.nextLine();
                System.out.println("Podaj hasło");
                String password = scanner.nextLine();
                registerUser(name, email, password, database);
                database.printDatabase();
            } catch (ValidationException e) {
                System.err.println("Błąd podczas dodawania danych użytkownika: " + e.getMessage());
            } catch (DuplicateEmailException | WeakPasswordException e) {
                System.err.println(e.getMessage());
            } finally {
                System.out.println("[1] - dodaj użytkownika\n[0] - wyjdź");
                option = scanner.nextInt();
                scanner.nextLine();
            }
        } while (option != 0);
    }

    private static void registerUser(String name, String email, String password, UserDatabase database) {
        if (!validateName(name)) {
            throw new ValidationException("Name", "W nazwie pojawiły się niedozwolone znaki");
        }

        if (!validateEmail(email, database)) {
            throw new ValidationException("Email", "Zła składnia emaila");
        }

        if (validatePassword(password) == PasswordStrength.WEAK) {
            throw new WeakPasswordException("Hasło zbyt słabe", PasswordStrength.WEAK);
        }

        User user = new User(name, email, hashPassword(password));
        database.add(user);
    }

    private static boolean validateName(String name) {
        if (name == null) {
            throw new ValidationException("Nazwa", "Nazwa nie istnieje");
        }

        if (name.trim().length() < 2 || name.trim().length() > 100) {
            throw new ValidationException("Nazwa", "Niepoprawna długość nazwy");
        }

        return name.trim().matches("[A-Za-z\\s-]+");
    }

    private static PasswordStrength validatePassword(String password) {
        int score = 0;

        if (password == null) {
            return PasswordStrength.WEAK;
        }

        if (password.length() >= 8) {
            score++;
        }

        if (password.matches(".*[^a-zA-Z0-9].*")) {
            score++;
        }

        if (password.matches(".*[A-Z].*")) {
            score++;
        }

        if (password.matches(".*[0-9].*")) {
            score++;
        }

        return switch (score) {
            case 3 -> PasswordStrength.OK;
            case 4 -> PasswordStrength.STRONG;
            default -> PasswordStrength.WEAK;
        };
    }

    private static boolean validateEmail(String email, UserDatabase database) {
        if (email == null) {
            throw new ValidationException("Email", "Email nie istnieje");
        }

        if (database.containsEmail(email)) {
            throw new DuplicateEmailException("email " + email + " jest już zajęty");
        }

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private static String hashPassword(String password) {
        return "hashed_" + password;
    }
}
