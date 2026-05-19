package pd11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UserRegistrationServiceTest {

    UserRepository userRepository = new UserRepository();

    @Test
    void shouldIsValidEmailReturnTrue() {
        //given
        String email = "filip.ostrowicki@wp.pl";

        //when
        var result = UserRegistrationService.isValidEmail(email, userRepository);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldIsValidEmailReturnFalse() {
        //given
        String email = "filip.ostrowickiwp.pl";

        //when
        var result = UserRegistrationService.isValidEmail(email, userRepository);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldIsValidEmailThrowValidationException() {
        //given
        String email = null;

        //then
        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> UserRegistrationService.isValidEmail(email, userRepository))
                .extracting(Throwable::getMessage).isEqualTo("Błąd podczas rejestracji użytkownika: Email nie istnieje");
    }

    @Test
    void shouldIsValidEmailReturnDuplicateEmailException() {
        //given
        UserRepository database = userRepository;
        database.add(new User("Filip", "filip.ostrowicki@wp.pl", "reee3"));
        String email = "filip.ostrowicki@wp.pl";

        assertThatExceptionOfType(DuplicateEmailException.class)
                .isThrownBy(() -> UserRegistrationService.isValidEmail(email, database));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void shouldResolvePasswordStrength(String password) {
        PasswordStrength result = UserRegistrationService.resolvePasswordStrength(password);

        assertThat(result).isGreaterThan(PasswordStrength.WEAK);
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                arguments("123qwFe777"),
                arguments("qwerty!12"),
                arguments("nvidni@2")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123"})
    @NullSource
    void shouldResolvePasswordStrengthReturnWeak(String password){
        PasswordStrength result = UserRegistrationService.resolvePasswordStrength(password);

        assertThat(result).isEqualByComparingTo(PasswordStrength.WEAK);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "haslo", "password"})
    void shouldHashPassword(String password) {
        String result = UserRegistrationService.hashPassword(password);

        assertThat(result).isEqualTo("hashed_" + password);
    }
}
