export class Book {
  id: number;
  title: string;
  author: string;
  description: string;
  tags: string;
  date: Date;


  constructor(id: number, title: string, author: string, description: string, tags: string, date: Date) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.description = description;
    this.tags = tags;
    this.date = date;
  }
}
