package pd11;

public class ValidationException extends RegistrationException {
    public ValidationException(String field, String message) {
        super(field + " - " + message);
    }
}
