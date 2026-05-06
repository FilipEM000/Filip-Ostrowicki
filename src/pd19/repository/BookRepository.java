package pd19.repository;

import pd19.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books = new ArrayList<>();

    public List<Book> findAll() {
        return books;
    }

    public void save(Book book) {
        books.add(book);
    }
}
