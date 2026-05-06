package pd19.dto;

public record BookDto(long id, String isbn, String title, String author, int year, int availableCopies) {
}
