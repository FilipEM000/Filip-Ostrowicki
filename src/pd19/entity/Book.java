package pd19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pd19.exception.BookNotAvailableException;

@Getter
@AllArgsConstructor
public class Book {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int availableCopies;

    public void borrow() {
        if (availableCopies <= 0) {
            throw new BookNotAvailableException("Ksążka " + title + " nie jest dostępna");
        }

        availableCopies--;
    }

    public void returnBack(){
        availableCopies++;
    }
}