package arobs.library.web.converter;

import arobs.library.core.model.Book;
import arobs.library.web.dto.BookWithoutCopiesDTO;
import org.springframework.stereotype.Component;

@Component
public class BookWithoutCopiesConverter extends BaseConverter<Book, BookWithoutCopiesDTO> {
    @Override
    public Book convertDtoToModel(BookWithoutCopiesDTO dto) {
        Book book = Book.builder()
                .author(dto.getAuthor())
                .date(dto.getDate())
                .description(dto.getDescription())
                .tags(dto.getTags())
                .title(dto.getTitle())
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookWithoutCopiesDTO convertModelToDto(Book book) {
        BookWithoutCopiesDTO dto = BookWithoutCopiesDTO.builder()
                .author(book.getAuthor())
                .date(book.getDate())
                .description(book.getDescription())
                .tags(book.getTags())
                .title(book.getTitle())
                .build();
        dto.setId(book.getId());
        return dto;
    }
}
