import {Book} from "./book.model";
import {Employee} from "./employee.model";
import {Copy} from "./copy.model";

export class BookRent{
  id: number;
  rentalDate: Date;
  returnDate: Date;
  status: string;
  grade: number;
  book: Book;
  employee: Employee;
  copy: Copy;


  constructor(id: number, rentalDate: Date, returnDate: Date, status: string, grade: number, book: Book, employee: Employee, copy: Copy) {
    this.id = id;
    this.rentalDate = rentalDate;
    this.returnDate = returnDate;
    this.status = status;
    this.grade = grade;
    this.book = book;
    this.employee = employee;
    this.copy = copy;
  }
}
