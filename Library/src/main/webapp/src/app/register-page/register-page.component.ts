import { Component, OnInit } from '@angular/core';
import {Employee} from "../shared/employee.model";
import {EmployeeService} from "../shared/employee.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  employee: Employee;
  role: string = '';
  constructor(private employeeService: EmployeeService,
              private router: Router) { }

  ngOnInit(): void {
  }

  register(name: string, email: string, password: string){
    if( name === '' || email === '' || this.role === '' || password === ''){
      alert("All fields must be filled!")
      return;
    }

    let employeeToRegister: Employee = new Employee(null, name, email, this.role, password, null);
    this.employeeService.register(employeeToRegister).subscribe(user => this.employee = user);

    if(this.employee === null){
      alert("Email already in use!");
      return;
    }

    this.router.navigate(["login-page"]);
  }

}
