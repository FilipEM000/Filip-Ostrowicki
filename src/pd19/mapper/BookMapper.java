package pd19.mapper;

import pd19.dto.BookDto;
import pd19.entity.Book;

public class BookMapper {
    public static BookDto mapToDto(Book book){
        return new BookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getYear(), book.getAvailableCopies());
    }

    public static Book mapToEntity(BookDto bookDto){
        return new Book(bookDto.id(), bookDto.isbn(), bookDto.title(), bookDto.author(), bookDto.year(), bookDto.availableCopies());
    }
}
