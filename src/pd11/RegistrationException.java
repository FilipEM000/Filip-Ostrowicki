package pd11;

public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) {
        super("Błąd podczas rejestracji użytkownika: " + message);
    }
}
