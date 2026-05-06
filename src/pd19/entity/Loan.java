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

    public boolean isOverdue(){
        return returnedAt == null && dueDate.isBefore(LocalDate.now());
    }
}
