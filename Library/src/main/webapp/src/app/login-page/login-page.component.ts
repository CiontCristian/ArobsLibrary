import { Component, OnInit } from '@angular/core';
import {Employee} from "../shared/employee.model";
import {EmployeeService} from "../shared/employee.service";
import {Router} from "@angular/router";
import {MenuComponent} from "../menu/menu.component";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  private employee: Employee;

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private menuComponent: MenuComponent) { }

  ngOnInit(): void {
  }

  login(email: string, password: string){
    if(email === '' || password === ''){
      alert("Please fill the inputs!")
      return;
    }

    this.employeeService.login(email, password)
      .subscribe(user => {this.employee = user;
        if(this.employee === null){
          alert("Invalid email or password!");
        }
        else{
          sessionStorage.setItem("user", JSON.stringify(this.employee));
          this.menuComponent.ngOnInit();
          this.router.navigate([""]);
        }
      });
  }

}
