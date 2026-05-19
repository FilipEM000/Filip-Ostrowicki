package pd12;

import java.util.Objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAccountV3 {
    private long id;
    private String email;
    private String displayName;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountV3 that = (UserAccountV3) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, displayName);
    }
}
