import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../shared/book.model";
import {BookService} from "../shared/book.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  @Input() book: Book;

  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => this.bookService.getOneBook(+params['id'])))
      .subscribe(book => this.book = book);
  }

  goBack(): void{
    this.location.back();
  }

  modify(): void{
    this.bookService.modifyBook(this.book).subscribe(_ => this.goBack());
  }

}
