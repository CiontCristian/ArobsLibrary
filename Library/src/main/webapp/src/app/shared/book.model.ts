import {Copy} from "./copy.model";

export class Book {
  id: number;
  title: string;
  author: string;
  description: string;
  tags: string;
  date: Date;
  copies: Copy[];


  constructor(id: number, title: string, author: string, description: string, tags: string, date: Date, copies: Copy[]) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.description = description;
    this.tags = tags;
    this.date = date;
    this.copies = copies;
  }
}
