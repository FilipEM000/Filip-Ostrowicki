package pd19.dto;

import java.time.LocalDate;

public record LoanDto (long id, BookDto book, MemberDto member, LocalDate borrowedAt, LocalDate dueDate, LocalDate returnedAt) {
}
