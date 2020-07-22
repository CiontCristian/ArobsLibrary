package arobs.library.web.converter;

import arobs.library.core.model.BaseEntity;
import arobs.library.web.dto.BaseDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public abstract class BaseConverter<Model extends BaseEntity<Long>, Dto extends BaseDTO> implements
                                                                                         Converter<Model, Dto> {

    public List<Long> convertModelsToIDs(List<Model> models) {
        return models.stream()
                .map(model -> model.getId())
                .collect(Collectors.toList());
    }

    public List<Long> convertDTOsToIDs(List<Dto> dtos) {
        return dtos.stream()
                .map(dto -> dto.getId())
                .collect(Collectors.toList());
    }

    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }

    public List<Model> convertDtosToModels(Collection<Dto> dtos) {
        return dtos.stream()
                .map(dto -> convertDtoToModel(dto))
                .collect(Collectors.toList());
    }
}
