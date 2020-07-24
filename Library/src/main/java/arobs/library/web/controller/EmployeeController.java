package arobs.library.web.controller;

import arobs.library.core.model.Employee;
import arobs.library.core.service.EmployeeService;
import arobs.library.web.converter.EmployeeConverter;
import arobs.library.web.dto.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeConverter employeeConverter;

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    List<EmployeeDTO> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();

        return employeeConverter.convertModelsToDtos(employees);
    }

    @RequestMapping(value = "/checkIfEmployeeExists", method = RequestMethod.POST)
    EmployeeDTO checkIfEmployeeExists(@RequestBody String[] args){
        String email = args[0];
        String password = args[1];

        Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
        logger.trace("In EmployeeController, method=checkIfEmployeeExists, employeeDTO={}", employee);
        if(employee.isPresent() && employee.get().getPassword().equals(password)){
            return employeeConverter.convertModelToDto(employee.get());
        }

        return null;
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        logger.trace("In EmployeeController, method=saveEmployee, employeeDTO={}", employeeDTO);
        Optional<Employee> employee = employeeService.getEmployeeByEmail(employeeDTO.getEmail());

        if(employee.isPresent()){
            return null;
        }
        return employeeConverter.convertModelToDto(employeeService.saveEmployee(
                employeeConverter.convertDtoToModel(employeeDTO)
        ));
    }

    @RequestMapping(value = "/modifyEmployee", method = RequestMethod.POST)
    EmployeeDTO modifyEmployee(@RequestBody EmployeeDTO employeeDTO){
        logger.trace("In EmployeeController, method=modifyEmployee, employeeDTO={}", employeeDTO);
        Employee employee = employeeConverter.convertDtoToModel(employeeDTO);

        Optional<Employee> updatedEmployee = employeeService.modifyEmployee(employee);

        if(updatedEmployee.isEmpty()){
            return null;
        }
        return employeeConverter.convertModelToDto(updatedEmployee.get());
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
    ResponseEntity<?> deleteEmployee(@RequestBody Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
