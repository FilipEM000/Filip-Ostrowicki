package pd19.service;

import pd19.entity.Loan;

import java.util.List;

public interface LoanService {
    Loan borrow(long memberId, long bookId);

    void returnBook(long loanId);

    List<Loan> findOverdue();
}
