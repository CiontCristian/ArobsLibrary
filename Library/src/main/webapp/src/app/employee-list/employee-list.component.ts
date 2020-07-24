import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../shared/employee.service";
import {Router} from "@angular/router";
import {Employee} from "../shared/employee.model";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Array<Employee>;
  selectedEmployee: Employee;

  constructor(private employeeService: EmployeeService,
              private router: Router) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(){
    this.employeeService.getAll()
      .subscribe(employees => this.employees = employees);
  }

  onSelect(employee: Employee){
    this.selectedEmployee = employee;
  }

  deleteEmployee(employee: Employee){
    this.employeeService.delete(employee.id)
      .subscribe(_ => {
        this.employees = this.employees.filter(e => e.id !== employee.id);
      });
  }

  goToDetails() {
    this.router.navigate(['employee-detail', this.selectedEmployee.id]);
  }
}
