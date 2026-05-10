package pd19.repository;

import pd19.entity.Loan;

import java.util.HashMap;
import java.util.Map;

public class LoanRepository {
    private Map<Long, Loan> loans = new HashMap<>();

    public Map<Long, Loan> findAll() {
        return loans;
    }

    public Loan findById(Long id) {
        return loans.get(id);
    }

    public void save(Loan loan) {
        if (loans.containsKey(loan.getId())) {
            throw new IllegalArgumentException("Wypożyczenie o id " + loan.getId() + " już istnieje");
        }
        loans.put(loan.getId(), loan);
    }

    public Long getNextId() {
        return (long) (loans.size() + 1);
    }
}