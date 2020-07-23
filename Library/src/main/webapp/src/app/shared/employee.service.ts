import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./employee.model";

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
}
