package arobs.library.web.converter;

import arobs.library.core.model.BaseEntity;
import arobs.library.web.dto.BaseDTO;


public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDTO> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

