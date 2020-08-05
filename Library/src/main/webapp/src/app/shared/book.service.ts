import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "./book.model";
import {map} from "rxjs/operators";
import {BookRent} from "./BookRent.model";
import {Copy} from "./copy.model";
import {RentRequest} from "./RentRequest.model";

@Injectable()
export class BookService {
  private bookURL = 'http://localhost:8080/book';
  private copyURL = 'http://localhost:8080/copy';

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

  rentBook(bookRent: BookRent): Observable<BookRent>{
    return this.httpClient.post<BookRent>(this.bookURL + '/rentBook', bookRent);
  }

  requestBookRent(rentRequest: RentRequest): Observable<RentRequest>{
    return this.httpClient.post<RentRequest>(this.bookURL + '/requestBookRent', rentRequest);
  }

  getAvailableCopies(bookID: number): Observable<Copy[]>{
    return this.httpClient.post<Copy[]>(this.copyURL + '/getAllAvailableCopies', bookID);
  }

  getRentedCopies(bookID: number): Observable<Copy[]>{
    return this.httpClient.post<Copy[]>(this.copyURL + '/getAllRentedCopies', bookID);
  }
}
