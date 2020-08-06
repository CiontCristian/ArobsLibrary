package arobs.library.web.controller;

import arobs.library.core.model.Employee;
import arobs.library.core.service.EmployeeService;
import arobs.library.web.converter.EmployeeWithRequestsConverter;
import arobs.library.web.converter.EmployeeWithoutRequestsConverter;
import arobs.library.web.dto.EmployeeWithRequestsDTO;
import arobs.library.web.dto.EmployeeWithoutRequestsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeWithoutRequestsConverter employeeWithoutRequestsConverter;
    @Autowired
    private EmployeeWithRequestsConverter employeeWithRequestsConverter;

    @RequestMapping(value = "/getAllEmployeesWithoutRequests", method = RequestMethod.GET)
    List<EmployeeWithoutRequestsDTO> getAllEmployeesWithoutRequests(){
        List<Employee> employees = employeeService.findAllEmployeesWithoutRequests();

        return employeeWithoutRequestsConverter.convertModelsToDtos(employees);
    }

    @RequestMapping(value = "/getAllEmployeesWithRequests", method = RequestMethod.GET)
    List<EmployeeWithRequestsDTO> getAllEmployeesWithRequests(){
        List<Employee> employees = employeeService.findAllEmployeesWithRequests();

        return employeeWithRequestsConverter.convertModelsToDtos(employees);
    }

    @RequestMapping(value = "/checkIfEmployeeExists", method = RequestMethod.POST)
    EmployeeWithoutRequestsDTO checkIfEmployeeExists(@RequestBody String[] args){
        String email = args[0];
        String password = args[1];

        Optional<Employee> employee = employeeService.findEmployeeByEmail(email);
        logger.trace("In EmployeeController, method=checkIfEmployeeExists, employeeDTO={}", employee);
        if(employee.isPresent() && employee.get().getPassword().equals(password)){
            return employeeWithoutRequestsConverter.convertModelToDto(employee.get());
        }

        return null;
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    EmployeeWithoutRequestsDTO saveEmployee(@RequestBody EmployeeWithoutRequestsDTO employeeDTO){
        logger.trace("In EmployeeController, method=saveEmployee, employeeDTO={}", employeeDTO);
        Optional<Employee> employee = employeeService.findEmployeeByEmail(employeeDTO.getEmail());

        if(employee.isPresent()){
            return null;
        }
        return employeeWithoutRequestsConverter.convertModelToDto(employeeService.saveEmployee(
                employeeWithoutRequestsConverter.convertDtoToModel(employeeDTO)
        ));
    }

    @RequestMapping(value = "/modifyEmployee", method = RequestMethod.POST)
    EmployeeWithoutRequestsDTO modifyEmployee(@RequestBody EmployeeWithoutRequestsDTO employeeDTO){
        logger.trace("In EmployeeController, method=modifyEmployee, employeeDTO={}", employeeDTO);
        Employee employee = employeeWithoutRequestsConverter.convertDtoToModel(employeeDTO);

        Optional<Employee> updatedEmployee = employeeService.modifyEmployee(employee);

        if(updatedEmployee.isEmpty()){
            return null;
        }
        return employeeWithoutRequestsConverter.convertModelToDto(updatedEmployee.get());
    }

    @RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllLateEmployees", method = RequestMethod.GET)
    List<EmployeeWithoutRequestsDTO> getAllLateEmployees(){
        List<Employee> employees = employeeService.getAllLateEmployees();

        return employeeWithoutRequestsConverter.convertModelsToDtos(employees);
    }

    @RequestMapping(value = "/topEmployeesByNrOfReadBooks", method = RequestMethod.GET)
    List<EmployeeWithoutRequestsDTO> getTopEmployeesByNrOfReadBooks() throws ParseException {
        Integer top = 2;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateformat.parse("2020-08-02");
        Date date2 = dateformat.parse("2020-10-11");

        List<Employee> employees = employeeService.topEmployeesByNrOfReadBooks(top, date1, date2);

        return employeeWithoutRequestsConverter.convertModelsToDtos(employees);
    }
}
