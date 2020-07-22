package arobs.library.web.converter;

import arobs.library.core.model.Copy;
import arobs.library.web.dto.CopyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CopyConverter extends BaseConverter<Copy, CopyDTO> {
    @Autowired
    private BookWithoutCopiesConverter bookWithoutCopiesConverter;

    @Override
    public Copy convertDtoToModel(CopyDTO dto) {
        Copy copy = Copy.builder()
                .code(dto.getCode())
                .isAvailable(dto.getIsAvailable())
                .status(dto.getStatus())
                .book(bookWithoutCopiesConverter.convertDtoToModel(dto.getBook()))
                .build();
        copy.setId(dto.getId());
        return copy;
    }

    @Override
    public CopyDTO convertModelToDto(Copy copy) {
        CopyDTO dto = CopyDTO.builder()
                .code(copy.getCode())
                .isAvailable(copy.getIsAvailable())
                .status(copy.getStatus())
                .book(bookWithoutCopiesConverter.convertModelToDto(copy.getBook()))
                .build();
        dto.setId(copy.getId());
        return dto;
    }
}
