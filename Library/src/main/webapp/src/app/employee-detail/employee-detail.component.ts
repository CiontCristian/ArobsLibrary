import {Component, Input, OnInit} from '@angular/core';
import {Employee} from "../shared/employee.model";
import {EmployeeService} from "../shared/employee.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent implements OnInit {

  @Input() employee: Employee;

  constructor(private employeeService: EmployeeService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => this.employeeService.getOne(+params['id'])))
      .subscribe(employee => this.employee = employee);
  }

  goBack(): void{
    this.location.back();
  }

  modify(): void{
    this.employeeService.modify(this.employee)
      .subscribe(_ => this.goBack());
  }

}
