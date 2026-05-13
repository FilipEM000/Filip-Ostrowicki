package pd11;

public class WeakPasswordException extends RegistrationException {
    public WeakPasswordException(String message, PasswordStrength score) {
        super(message + " " + score);
    }
}
