package arobs.library.web.converter;

import arobs.library.core.model.BookRent;
import arobs.library.core.model.BookRequest;
import arobs.library.web.dto.BookRentDTO;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookRentConverter extends BaseConverter<BookRent, BookRentDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookRent convertDtoToModel(BookRentDTO dto) {
        BookRent bookRent = modelMapper.map(dto, BookRent.class);
        dto.setId(dto.getId());
        return bookRent;
    }

    @Override
    public BookRentDTO convertModelToDto(BookRent bookRent) {
        BookRentDTO dto = modelMapper.map(bookRent, BookRentDTO.class);
        dto.setId(bookRent.getId());
        return dto;
    }
}
