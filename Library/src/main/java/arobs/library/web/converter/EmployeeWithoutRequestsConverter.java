package arobs.library.web.converter;

import arobs.library.core.model.Employee;
import arobs.library.web.dto.EmployeeWithoutRequestsDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWithoutRequestsConverter extends BaseConverter<Employee, EmployeeWithoutRequestsDTO> {
    @Override
    public Employee convertDtoToModel(EmployeeWithoutRequestsDTO dto) {
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
    public EmployeeWithoutRequestsDTO convertModelToDto(Employee employee) {
        EmployeeWithoutRequestsDTO dto = EmployeeWithoutRequestsDTO.builder()
                .email(employee.getEmail())
                .name(employee.getName())
                .password(employee.getPassword())
                .role(employee.getRole())
                .build();

        dto.setId(employee.getId());
        return dto;
    }
}
