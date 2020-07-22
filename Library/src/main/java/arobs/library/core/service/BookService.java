package arobs.library.core.service;

import arobs.library.core.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooksWithoutCopies();
    List<Book> findAllBooksWithCopies();
    Book saveBook(Book book);
    Optional<Book> modifyBook(Book book);
    void deleteBook(Long id);
}
