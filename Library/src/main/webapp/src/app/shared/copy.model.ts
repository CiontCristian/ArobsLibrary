import {Book} from "./book.model";

export class Copy{
  code: number;
  isAvailable: boolean;
  status: string;
  book: Book;


  constructor(code: number, isAvailable: boolean, status: string, book: Book) {
    this.code = code;
    this.isAvailable = isAvailable;
    this.status = status;
    this.book = book;
  }
}
