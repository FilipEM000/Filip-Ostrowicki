package pd19.service;

import pd19.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book boob);

    Book findByIsbn(String isbn);

    Book findById(long id);

    List<Book> findAvailable();

    List<Book> search(String query);
}
