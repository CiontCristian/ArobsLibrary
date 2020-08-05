import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../shared/book.model";
import {BookService} from "../shared/book.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {Copy} from "../shared/copy.model";
import {BookRent} from "../shared/BookRent.model";
import {Employee} from "../shared/employee.model";
import {RentRequest} from "../shared/RentRequest.model";

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  @Input() book: Book;
  availableCopies: Copy[] = null;
  rentedCopies: Copy[] = null;
  currentUser: Employee = JSON.parse(sessionStorage.getItem("user"));

  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => this.bookService.getOneBook(+params['id'])))
      .subscribe(book => {
        this.book = book;
        this.bookService.getAvailableCopies(this.book.id).subscribe(copies => this.availableCopies = copies);
        this.bookService.getRentedCopies(this.book.id).subscribe(copies => this.rentedCopies = copies);
      });
  }

  goBack(): void{
    this.location.back();
  }

  modify(): void{
    this.bookService.modifyBook(this.book).subscribe(_ => this.goBack());
  }

  rentBook() {
    var returnDate = new Date();
    returnDate.setMonth(returnDate.getMonth() + 1);

    let bookRent: BookRent = new BookRent(-1, new Date(), new Date(returnDate), "on_going", null, this.book, this.currentUser,
      this.availableCopies[Math.floor(Math.random() * this.availableCopies.length)]);

    this.bookService.rentBook(bookRent).subscribe();
  }

  requestRent() {
    let requestRent: RentRequest = new RentRequest(-1, new Date(), "waiting for available copy", this.book,
      this.currentUser, this.rentedCopies[Math.floor(Math.random() * this.rentedCopies.length)]);

    this.bookService.requestBookRent(requestRent).subscribe();
  }
}
