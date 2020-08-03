package arobs.library.core.service;

import arobs.library.core.model.Book;
import arobs.library.core.model.BookRequest;
import arobs.library.core.model.RentRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {
    //Methods for Book Entity
    List<Book> findAllBooksWithoutCopies();
    List<Book> findAllBooksWithCopies();
    Optional<Book> findBookByTitle(String title);
    Optional<Book> findOne(Long id);
    Book saveBook(Book book);
    Optional<Book> modifyBook(Book book);
    void deleteBook(Long id);
    Boolean checkAvailableCopies(Book book);

    //Methods for BookRequest Entity
    Optional<BookRequest> saveBookRequest(BookRequest bookRequest);
    List<BookRequest> getAllBookRequests();

    //Methods for RentRequest Entity
    Optional<RentRequest> saveRentRequest(RentRequest rentRequest);
    void deleteRentRequest(Long id);
    Optional<RentRequest> updateRentRequestStatus(RentRequest rentRequest);
    List<RentRequest> getAllRentRequests();
}
