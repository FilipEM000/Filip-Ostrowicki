package pd19.service;

import pd19.dto.CreateLoanRequest;
import pd19.dto.LoanDto;
import pd19.dto.ReturnBookRequest;
import pd19.entity.Loan;

import java.util.List;

public interface LoanService {
    LoanDto borrow(CreateLoanRequest request);

    void returnBook(ReturnBookRequest request);

    List<LoanDto> findOverdue();
}
