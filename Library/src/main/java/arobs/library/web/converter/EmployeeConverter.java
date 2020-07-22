package arobs.library.web.converter;

import arobs.library.core.model.Employee;
import arobs.library.web.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends BaseConverter<Employee, EmployeeDTO> {
    @Override
    public Employee convertDtoToModel(EmployeeDTO dto) {
        Employee employee = Employee.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();

        employee.setId(dto.getId());
        return employee;
    }

    @Override
    public EmployeeDTO convertModelToDto(Employee employee) {
        EmployeeDTO dto = EmployeeDTO.builder()
                .email(employee.getEmail())
                .name(employee.getName())
                .password(employee.getPassword())
                .role(employee.getRole())
                .build();

        dto.setId(employee.getId());
        return dto;
    }
}
