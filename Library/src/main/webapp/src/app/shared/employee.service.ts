import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./employee.model";
import {map} from "rxjs/operators";

@Injectable()
export class EmployeeService{
  private employeeURL = 'http://localhost:8080/employee';

  constructor(private httpClient: HttpClient) {
  }

  login(email: string, password: string): Observable<Employee>{
    return this.httpClient
      .post<Employee>(this.employeeURL + '/checkIfEmployeeExists', [email, password]);
  }

  register(employee: Employee): Observable<Employee>{
    return this.httpClient
      .post<Employee>(this.employeeURL + '/saveEmployee', employee);
  }

  modify(employee: Employee): Observable<Employee>{
    return this.httpClient
      .post<Employee>(this.employeeURL + '/modifyEmployee', employee);
  }

  delete(id: number): Observable<any>{
    const url = `${this.employeeURL}/deleteEmployee/${id}`;

    return this.httpClient
      .delete(url);
  }

  getAll(): Observable<Employee[]>{
    return this.httpClient.get<Array<Employee>>(this.employeeURL + "/getAllEmployees");
  }

  getOne(id: number): Observable<Employee>{
    return this.getAll().pipe(
      map(employees => employees.find(employee => employee.id === id))
    );
  }
}
