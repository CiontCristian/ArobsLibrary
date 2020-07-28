package arobs.library.core.repository;

import arobs.library.core.model.Book;
import arobs.library.core.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JPARepository<Employee, Long> {
    @Query("select e from Employee e")
    @EntityGraph(value = "employeeWithRequests", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Employee> findAllWithRequests();

    @Query("select e from Employee e")
    List<Employee> findAllWithoutRequests();
}
