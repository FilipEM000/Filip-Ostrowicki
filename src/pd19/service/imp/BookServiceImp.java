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
        return bookRepository.findAll().values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .map(BookMapper::mapToDto)
                .orElseThrow(() -> new BookNotFoundException("Nie znaleziono książki o numerze isbn " + isbn));
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id);

        if(book == null){
            throw new BookNotFoundException("Nie odnaleziono książki o id " + id);
        }
        return BookMapper.mapToDto(book);
    }

    @Override
    public List<BookDto> findAvailable() {
        return bookRepository.findAll().values().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .map(BookMapper::mapToDto)
                .toList();
    }

    @Override
    public List<BookDto> search(String query) {
        return bookRepository.findAll().values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                        book.getTitle().toLowerCase().contains(query.toLowerCase()))
                .map(BookMapper::mapToDto)
                .toList();
    }

    @Override
    public Book findEntityById(Long id) {
        Book book = bookRepository.findById(id);

        if(book == null){
            throw new BookNotFoundException("Nie odnaleziono książki o id " + id);
        }
        return book;
    }
}