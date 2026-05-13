package pd11;

public class DuplicateEmailException extends RegistrationException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
