package pd19.service.imp;

import pd19.entity.Book;
import pd19.exception.BookNotFoundException;
import pd19.exception.MemberNotFoundException;
import pd19.repository.BookRepository;
import pd19.service.BookService;

import java.util.List;

public class BookServiceImp implements BookService {
    private BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Nie znaleziono książki o numerze isbn " + isbn));
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findAll().stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("Nie odnaleziono książki o id " + id));
    }

    @Override
    public List<Book> findAvailable() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .toList();
    }

    @Override
    public List<Book> search(String query) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                        book.getTitle().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}
