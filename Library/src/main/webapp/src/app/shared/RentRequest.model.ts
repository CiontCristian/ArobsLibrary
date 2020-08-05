import {Book} from "./book.model";
import {Employee} from "./employee.model";
import {Copy} from "./copy.model";

export class RentRequest{
  id: number;
  requestDate: Date;
  status: string;
  book: Book;
  employee: Employee;
  copy: Copy;


  constructor(id: number, requestDate: Date, status: string, book: Book, employee: Employee, copy: Copy) {
    this.id = id;
    this.requestDate = requestDate;
    this.status = status;
    this.book = book;
    this.employee = employee;
    this.copy = copy;
  }
}
