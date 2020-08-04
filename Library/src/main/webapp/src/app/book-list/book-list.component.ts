import { Component, OnInit } from '@angular/core';
import {Book} from "../shared/book.model";
import {BookService} from "../shared/book.service";
import {Router} from "@angular/router";
import {BookRent} from "../shared/BookRent.model";
import {Employee} from "../shared/employee.model";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  books: Array<Book>;
  selectedBook: Book;

  constructor(private bookService: BookService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllBooks();
  }

  getAllBooks(){
    this.bookService
      .getAllBooks().subscribe(books => this.books = books);
  }

  onSelect(book: Book): void {
    this.selectedBook = book;
  }

  gotoDetail(): void{
    this.router.navigate(['book-detail', this.selectedBook.id]);
  }

  addNewBook() {
    this.router.navigate(['book-new']);
  }

}
