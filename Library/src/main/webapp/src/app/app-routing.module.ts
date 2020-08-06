import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MainPageComponent} from "./main-page/main-page.component";
import {LoginPageComponent} from "./login-page/login-page.component";
import {RegisterPageComponent} from "./register-page/register-page.component";
import {BookListComponent} from "./book-list/book-list.component";
import {EmployeeListComponent} from "./employee-list/employee-list.component";
import {EmployeeDetailComponent} from "./employee-detail/employee-detail.component";
import {BookDetailComponent} from "./book-detail/book-detail.component";
import {BookNewComponent} from "./book-new/book-new.component";
import {BookRequestComponent} from "./book-request/book-request.component";
import {StatisticsComponent} from "./statistics/statistics.component";


const routes: Routes = [
  {path: '', component:MainPageComponent},
  {path: 'login-page', component: LoginPageComponent},
  {path: 'register-page', component: RegisterPageComponent},
  {path: 'book-list', component: BookListComponent},
  {path: 'book-detail/:id', component: BookDetailComponent},
  {path: 'book-new', component: BookNewComponent},
  {path: 'employee-list', component: EmployeeListComponent},
  {path: 'employee-detail/:id', component: EmployeeDetailComponent},
  {path: 'book-request', component: BookRequestComponent},
  {path: 'statistics', component: StatisticsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
