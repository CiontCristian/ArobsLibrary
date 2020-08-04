package arobs.library.core.service;

import arobs.library.core.model.*;
import arobs.library.core.repository.BookRentRepository;
import arobs.library.core.repository.BookRepository;
import arobs.library.core.repository.BookRequestRepository;
import arobs.library.core.repository.RentRequestRepository;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    public static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CopyService copyService;
    @Autowired
    private BookRequestRepository bookRequestRepository;
    @Autowired
    private RentRequestRepository rentRequestRepository;
    @Autowired
    private BookRentRepository bookRentRepository;

    @Override
    public List<Book> findAllBooksWithoutCopies() {
        return bookRepository.findAllWithoutCopies();
    }

    @Override
    public List<Book> findAllBooksWithCopies() {
        return bookRepository.findAllWithCopies();
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public Optional<Book> findOne(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Optional<Book> modifyBook(Book book) {
        Book newBook = bookRepository.findById(book.getId()).orElse(null);
        if(newBook == null)
            return Optional.empty();

        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setDate(book.getDate());
        newBook.setDescription(book.getDescription());
        newBook.setTags(book.getTags());

        return Optional.of(newBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Boolean checkAvailableCopies(Book book) {
        logger.trace("In BookService, method=checkAvailableCopies, book={}", book);
        //Hibernate.initialize(book.getCopies());
        long nrOfAvailableCopies = book.getCopies()
                .stream()
                .filter(copy -> copy.getStatus().equals("Available") && copy.getIsAvailable())
                .count();

        return nrOfAvailableCopies > 0;
    }

    @Override
    public Optional<BookRequest> saveBookRequest(BookRequest bookRequest) {
        Optional<Book> book = findBookByTitle(bookRequest.getTitle());
        if(book.isPresent()){
            return Optional.empty();
        }

        return Optional.of(bookRequestRepository.save(bookRequest));
    }

    @Override
    public List<BookRequest> getAllBookRequests() {
        return bookRequestRepository.findAllWithoutEmployee();
    }

    @Override
    public Optional<RentRequest> saveRentRequest(RentRequest rentRequest) {
        if(checkAvailableCopies(rentRequest.getBook())){
            return Optional.empty();
        }

        return Optional.of(rentRequestRepository.save(rentRequest));
    }

    @Override
    public void deleteRentRequest(Long id) {
        rentRequestRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<RentRequest> updateRentRequestStatus(RentRequest rentRequest) {
        RentRequest newRentRequest = rentRequestRepository.findById(rentRequest.getId()).orElse(null);
        if(newRentRequest == null){
            return Optional.empty();
        }

        newRentRequest.setStatus(rentRequest.getStatus());
        return Optional.of(newRentRequest);
    }

    @Override
    public List<RentRequest> getAllRentRequests() {
        return rentRequestRepository.findAll();
    }

    @Override
    public Optional<BookRent> saveBookRent(BookRent bookRent) {
        if(!checkAvailableCopies(bookRent.getBook())){
            logger.trace("In BookService, method=saveBookRent, There is a rent request for the book={}", bookRent.getBook());
            return Optional.empty();
        }
        Copy copy = bookRent.getCopy();
        copy.setStatus("Rented");

        copyService.modifyCopy(copy);

        return Optional.of(bookRentRepository.save(bookRent));
    }

    @Override
    public void deleteBookRent(Long id) {
        bookRentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<BookRent> modifyBookRent(BookRent bookRent) {
        BookRent newBookRent = bookRentRepository.findById(bookRent.getId()).orElse(null);
        if(newBookRent == null){
            return Optional.empty();
        }

        newBookRent.setStatus(bookRent.getStatus());
        newBookRent.setGrade(bookRent.getGrade());

        return Optional.of(newBookRent);
    }

    @Override
    public List<BookRent> getAllBookRents() {
        return bookRentRepository.findAll();
    }
}
