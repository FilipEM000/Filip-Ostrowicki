package pd19.repository;

import pd19.entity.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    private List<Loan> loans = new ArrayList<>();

    public List<Loan> findAll() {
        return loans;
    }

    public void save(Loan loan) {
        loans.add(loan);
    }
}
