package arobs.library.core.service;



import arobs.library.core.model.BookRequest;
import arobs.library.core.model.Employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAllEmployeesWithRequests();
    List<Employee> findAllEmployeesWithoutRequests();
    Employee saveEmployee(Employee employee);
    Optional<Employee> modifyEmployee(Employee employee);
    void deleteEmployee(Long id);
    Optional<Employee> findEmployeeByEmail(String email);

    //statistics
    List<Employee> getAllLateEmployees();
    Long getNrOfReadBooksForEmployee(Employee employee, Date startDate, Date endDate);
    List<Employee> topEmployeesByNrOfReadBooks(Integer top, Date startDate, Date endDate);
}
