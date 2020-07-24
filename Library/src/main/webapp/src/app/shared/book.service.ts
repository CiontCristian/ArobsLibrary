import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class BookService {
  private bookURL = 'http://localhost:8080/book';

  constructor(private httpClient: HttpClient) {
  }
}
