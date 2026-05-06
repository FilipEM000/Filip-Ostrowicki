package pd19.service;

import pd19.dto.BookDto;
import pd19.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(BookDto bookDto);

    BookDto findByIsbn(String isbn);

    BookDto findById(long id);

    List<BookDto> findAvailable();

    List<BookDto> search(String query);

    Book findEntityById(long id);
}
