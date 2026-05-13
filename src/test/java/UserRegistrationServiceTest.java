import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pd11.UserRegistrationService;
import pd11.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserRegistrationServiceTest {

    @Mock
    UserRepository userRepository = new UserRepository();

    @InjectMocks
    UserRegistrationService userRegistrationService;

    @Test
    void shouldValidateEmailReturnTrue(){
        //given
        String email = "filip.ostrowicki@wp.pl";
        UserRepository database = userRepository;

        //when
        var result = UserRegistrationService.validateEmail(email, database);

        assertThat(result).isTrue();
    }
}
