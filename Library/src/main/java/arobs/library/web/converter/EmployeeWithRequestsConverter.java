package arobs.library.web.converter;

import arobs.library.core.model.Employee;
import arobs.library.web.dto.EmployeeWithRequestsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWithRequestsConverter extends BaseConverter<Employee, EmployeeWithRequestsDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Employee convertDtoToModel(EmployeeWithRequestsDTO dto) {
        Employee employee = modelMapper.map(dto, Employee.class);
        employee.setId(dto.getId());
        return employee;
    }

    @Override
    public EmployeeWithRequestsDTO convertModelToDto(Employee employee) {
        EmployeeWithRequestsDTO dto = modelMapper.map(employee, EmployeeWithRequestsDTO.class);
        dto.setId(employee.getId());
        return dto;
    }
}
