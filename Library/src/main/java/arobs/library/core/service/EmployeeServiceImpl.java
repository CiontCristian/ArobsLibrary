package arobs.library.core.service;

import arobs.library.core.model.BookRequest;
import arobs.library.core.model.Employee;
import arobs.library.core.repository.BookRequestRepository;
import arobs.library.core.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployeesWithRequests() {
        return employeeRepository.findAllWithRequests();
    }

    @Override
    public List<Employee> findAllEmployeesWithoutRequests() {
        return employeeRepository.findAllWithoutRequests();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Optional<Employee> modifyEmployee(Employee employee) {
        Employee newEmployee = employeeRepository.findById(employee.getId()).orElse(null);

        if(newEmployee == null)
            return Optional.empty();

        newEmployee.setName(employee.getName());
        newEmployee.setRole(employee.getRole());
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setPassword(employee.getPassword());

        return Optional.of(newEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getEmail().equals(email)).findFirst();
    }
}
