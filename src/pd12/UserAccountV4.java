package pd12;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class UserAccountV4 {
    private long id;
    private String email;
    private String displayName;
    @Setter
    private String status;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountV4 that = (UserAccountV4) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(displayName, that.displayName) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, displayName, status);
    }
}
