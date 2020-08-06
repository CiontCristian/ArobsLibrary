package arobs.library.core.service;

import arobs.library.core.model.BookRequest;
import arobs.library.core.model.Employee;
import arobs.library.core.repository.BookRentRepository;
import arobs.library.core.repository.BookRequestRepository;
import arobs.library.core.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BookRentRepository bookRentRepository;

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

    @Override
    public List<Employee> getAllLateEmployees() {
        Set<Long> lateEmployeeIDs = bookRentRepository.findAll().stream()
                .filter(bookRent -> bookRent.getStatus().equals("Late"))
                .map(bookRent -> bookRent.getEmployee().getId())
                .collect(Collectors.toSet());

        return employeeRepository.findAllById(lateEmployeeIDs);
    }

    @Override
    public Long getNrOfReadBooksForEmployee(Employee employee, Date startDate, Date endDate) {
        return bookRentRepository.findAll().stream()
                .filter(bookRent -> bookRent.getEmployee().getId().equals(employee.getId()))
                .filter(bookRent -> bookRent.getRentalDate().after(startDate) && bookRent.getReturnDate().before(endDate))
                .count();
    }

    @Override
    public List<Employee> topEmployeesByNrOfReadBooks(Integer top, Date startDate, Date endDate) {
        Map<Employee, Long> map = new HashMap<>();
        List<Employee> employees = employeeRepository.findAllWithoutRequests();
        for(Employee employee: employees){
            if(getNrOfReadBooksForEmployee(employee, startDate, endDate) > 0)
                map.put(employee, getNrOfReadBooksForEmployee(employee, startDate, endDate));
        }

        List<Employee> topEmployees;

        topEmployees = map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(top)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return topEmployees;
    }
}
