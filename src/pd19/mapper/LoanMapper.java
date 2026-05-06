package pd19.mapper;

import pd19.dto.LoanDto;
import pd19.entity.Loan;

public class LoanMapper {
    public static LoanDto mapToDto(Loan loan){
        return new LoanDto(loan.getId(), BookMapper.mapToDto(loan.getBook()), loan.getMember().getName(), loan.getBorrowedAt(), loan.getDueDate(), loan.getReturnedAt());
    }
}
