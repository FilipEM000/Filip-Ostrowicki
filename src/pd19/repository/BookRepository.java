package pd19.repository;

import pd19.entity.Book;

import java.util.HashMap;
import java.util.Map;

public class BookRepository {
    private Map<Long, Book> books = new HashMap<>();

    public Map<Long, Book> findAll() {
        return books;
    }

    public void save(Book book) {
        books.put(book.getId(), book);
    }

    public Book findById(Long id){
        return books.get(id);
    }
}
