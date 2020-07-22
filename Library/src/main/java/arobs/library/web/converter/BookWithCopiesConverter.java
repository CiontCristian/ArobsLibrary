package arobs.library.web.converter;

import arobs.library.core.model.Book;
import arobs.library.web.dto.BookWithCopiesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookWithCopiesConverter extends BaseConverter<Book, BookWithCopiesDTO> {
    @Autowired
    private CopyConverter copyConverter;

    @Override
    public Book convertDtoToModel(BookWithCopiesDTO dto) {
        Book book = Book.builder()
                .author(dto.getAuthor())
                .date(dto.getDate())
                .description(dto.getDescription())
                .tags(dto.getTags())
                .title(dto.getTitle())
                .copies(copyConverter.convertDtosToModels(dto.getCopies()))
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookWithCopiesDTO convertModelToDto(Book book) {
        BookWithCopiesDTO dto = BookWithCopiesDTO.builder()
                .author(book.getAuthor())
                .date(book.getDate())
                .description(book.getDescription())
                .tags(book.getTags())
                .title(book.getTitle())
                .copies(copyConverter.convertModelsToDtos(book.getCopies()))
                .build();
        dto.setId(book.getId());
        return dto;
    }
}
