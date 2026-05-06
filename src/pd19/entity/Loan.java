package pd19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class Loan {
    private long id;
    private Book book;
    private Member member;
    private LocalDate borrowedAt;
    private LocalDate dueDate;
    private LocalDate returnedAt;

    public Loan(long id, Book book, Member member, LocalDate borrowedAt, LocalDate dueDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowedAt = borrowedAt;
        this.dueDate = dueDate;
        this.returnedAt = null;
    }

    public boolean isOverdue() {
        return isRented() && dueDate.isBefore(LocalDate.now());
    }

    public boolean isRented() {
        return returnedAt == null;
    }
}
