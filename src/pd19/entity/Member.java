package pd19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Member {
    private long id;
    private String name;
    private String email;
    private List<Loan> loans;

    public boolean canBorrow(){
        return loans.stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .count() < 3;
    }
}
