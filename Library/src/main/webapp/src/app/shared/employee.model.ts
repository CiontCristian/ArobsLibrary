import {BookRequest} from "./BookRequest.model";

export class Employee{
  id: number;
  name: string;
  email: string;
  role: string;
  password: string;
  requests: BookRequest[];


  constructor(id: number, name: string, email: string, role: string, password: string, requests: BookRequest[]) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
    this.password = password;
    this.requests = requests;
  }
}
