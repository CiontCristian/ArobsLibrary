package arobs.library.web.converter;

import arobs.library.core.model.BookRequest;
import arobs.library.web.dto.BookRequestDTO;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookRequestConverter extends BaseConverter<BookRequest, BookRequestDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookRequest convertDtoToModel(BookRequestDTO dto) {
        BookRequest bookRequest = modelMapper.map(dto, BookRequest.class);
        bookRequest.setId(dto.getId());
        return bookRequest;
    }

    @Override
    public BookRequestDTO convertModelToDto(BookRequest bookRequest) {
        BookRequestDTO dto = modelMapper.map(bookRequest, BookRequestDTO.class);
        dto.setId(bookRequest.getId());
        return dto;
    }
}
