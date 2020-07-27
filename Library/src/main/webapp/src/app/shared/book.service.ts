import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "./book.model";
import {map} from "rxjs/operators";

@Injectable()
export class BookService {
  private bookURL = 'http://localhost:8080/book';

  constructor(private httpClient: HttpClient) {
  }

  getAllBooks(): Observable<Book[]>{
    return this.httpClient.get<Array<Book>>(this.bookURL+ '/getAllBooksWithCopies');
  }

  getOneBook(id: number): Observable<Book>{
    return this.getAllBooks().pipe(map(books => books.find(book => book.id === id)));
  }

  saveBook(book: Book): Observable<Book>{
    return this.httpClient.post<Book>(this.bookURL + '/saveBook', book);
  }

  modifyBook(book: Book): Observable<Book>{
    return this.httpClient.post<Book>(this.bookURL + '/modifyBook', book);
  }
}
