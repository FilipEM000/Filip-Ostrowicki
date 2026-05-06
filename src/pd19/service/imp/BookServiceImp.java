package pd19.service.imp;

import pd19.dto.BookDto;
import pd19.entity.Book;
import pd19.exception.BookNotFoundException;
import pd19.exception.MemberNotFoundException;
import pd19.mapper.BookMapper;
import pd19.repository.BookRepository;
import pd19.service.BookService;

import java.util.List;

public class BookServiceImp implements BookService {
    private BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(BookDto bookDto) {
        bookRepository.save(BookMapper.mapToEntity(bookDto));
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .map(BookMapper::mapToDto)
                .orElseThrow(() -> new BookNotFoundException("Nie znaleziono książki o numerze isbn " + isbn));
    }

    @Override
    public BookDto findById(long id) {
        return bookRepository.findAll().stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .map(BookMapper::mapToDto)
                .orElseThrow(() -> new MemberNotFoundException("Nie odnaleziono książki o id " + id));
    }

    @Override
    public List<BookDto> findAvailable() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .map(BookMapper::mapToDto)
                .toList();
    }

    @Override
    public List<BookDto> search(String query) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                        book.getTitle().toLowerCase().contains(query.toLowerCase()))
                .map(BookMapper::mapToDto)
                .toList();
    }

    @Override
    public Book findEntityById(long id) {
        return bookRepository.findAll().stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("Nie odnaleziono książki o id " + id));
    }
}