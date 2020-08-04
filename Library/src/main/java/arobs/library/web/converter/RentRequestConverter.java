package arobs.library.web.converter;

import arobs.library.core.model.RentRequest;
import arobs.library.web.dto.RentRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentRequestConverter extends BaseConverter<RentRequest, RentRequestDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RentRequest convertDtoToModel(RentRequestDTO dto) {
        RentRequest rentRequest = modelMapper.map(dto, RentRequest.class);
        rentRequest.setId(dto.getId());
        return rentRequest;
    }

    @Override
    public RentRequestDTO convertModelToDto(RentRequest rentRequest) {
        RentRequestDTO dto = modelMapper.map(rentRequest, RentRequestDTO.class);
        dto.setId(rentRequest.getId());
        return dto;
    }
}
