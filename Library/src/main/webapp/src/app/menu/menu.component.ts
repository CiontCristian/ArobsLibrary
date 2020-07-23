import { Component, OnInit } from '@angular/core';
import {Employee} from "../shared/employee.model";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  employee: Employee;
  constructor() { }

  ngOnInit(): void {
    this.employee = JSON.parse(sessionStorage.getItem("user"));
  }

  logout(): void{
    sessionStorage.clear();
    this.ngOnInit();
  }

}
