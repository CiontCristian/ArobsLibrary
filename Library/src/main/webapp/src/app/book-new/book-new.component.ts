import { Component, OnInit } from '@angular/core';
import {BookService} from "../shared/book.service";
import {Location} from "@angular/common";
import {Book} from "../shared/book.model";

@Component({
  selector: 'app-book-new',
  templateUrl: './book-new.component.html',
  styleUrls: ['./book-new.component.css']
})
export class BookNewComponent implements OnInit {

  constructor(private bookService: BookService,
              private location: Location) { }

  ngOnInit(): void {
  }

  saveBook(title: string, author: string, tags: string, description: string){
    let newBook: Book = new Book(-1, title,
      author, description, tags, new Date(), null);

    this.bookService.saveBook(newBook).subscribe();

    this.location.back();
  }

}
