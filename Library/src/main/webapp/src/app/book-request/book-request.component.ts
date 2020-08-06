import { Component, OnInit } from '@angular/core';
import {Employee} from "../shared/employee.model";
import {BookService} from "../shared/book.service";
import {Router} from "@angular/router";
import {BookRequest} from "../shared/BookRequest.model";

@Component({
  selector: 'app-book-request',
  templateUrl: './book-request.component.html',
  styleUrls: ['./book-request.component.css']
})
export class BookRequestComponent implements OnInit {

  currentUser: Employee = JSON.parse(sessionStorage.getItem("user"));
  constructor(private bookService: BookService,
              private router: Router) { }

  ngOnInit(): void {
  }

  requestBook(title: string, author: string, publisher: string, website: string, status: string, cost: string, copies: string) {
    let bookRequest: BookRequest = new BookRequest(-1,
      title, author, publisher, website, +copies, +cost, status, this.currentUser);

    this.bookService.requestBook(bookRequest).subscribe();
  }
}
