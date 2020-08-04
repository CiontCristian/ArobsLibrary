import {Employee} from "./employee.model";

export class BookRequest{
  id: number;
  title: string;
  author: string;
  publisher: string;
  website: string;
  nrOfCopies: number;
  cost: number;
  status: string;
  employee: Employee;


  constructor(id: number, title: string, author: string, publisher: string, website: string, nrOfCopies: number, cost: number, status: string, employee: Employee) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.website = website;
    this.nrOfCopies = nrOfCopies;
    this.cost = cost;
    this.status = status;
    this.employee = employee;
  }
}
