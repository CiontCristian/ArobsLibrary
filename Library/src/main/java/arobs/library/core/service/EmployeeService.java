package arobs.library.core.service;



import arobs.library.core.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAllEmployeesWithRequests();
    List<Employee> findAllEmployeesWithoutRequests();
    Employee saveEmployee(Employee employee);
    Optional<Employee> modifyEmployee(Employee employee);
    void deleteEmployee(Long id);
    Optional<Employee> getEmployeeByEmail(String email);
}
