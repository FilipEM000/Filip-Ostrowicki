package pd19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String email;
    private List<Loan> loans;

    public boolean canBorrow() {
        return loans.stream()
                .filter(Loan::isRented)
                .count() < 3;
    }
}