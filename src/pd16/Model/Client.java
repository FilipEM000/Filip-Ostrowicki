package pd16.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "of")
@Data
public class Client {
    private final String name;
    private String email;
}
