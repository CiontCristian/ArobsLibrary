package arobs.library.web.controller;

import arobs.library.core.model.Book;
import arobs.library.core.model.BookRent;
import arobs.library.core.model.BookRequest;
import arobs.library.core.model.RentRequest;
import arobs.library.core.service.BookService;
import arobs.library.web.converter.*;
import arobs.library.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.OptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    public static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private BookWithoutCopiesConverter bookWithoutCopiesConverter;
    @Autowired
    private BookWithCopiesConverter bookWithCopiesConverter;
    @Autowired
    private BookRentConverter bookRentConverter;
    @Autowired
    private RentRequestConverter rentRequestConverter;
    @Autowired
    private BookRequestConverter bookRequestConverter;

    @RequestMapping(value = "/getAllBooksWithoutCopies", method = RequestMethod.GET)
    List<BookWithoutCopiesDTO> getAllBooksWithoutCopies(){
        logger.trace("In BookController, method=getAllBooksWithoutCopies");
        List<Book> books = bookService.findAllBooksWithoutCopies();
        //logger.trace("In BookController, method=getAllBooksWithoutCopies, books={}", books);
        return new ArrayList<>(bookWithoutCopiesConverter.convertModelsToDtos(books));
    }

    @RequestMapping(value = "/getAllBooksWithCopies", method = RequestMethod.GET)
    List<BookWithCopiesDTO> getAllBooksWithCopies(){
        logger.trace("In BookController, method=getAllBooksWithCopies");
        List<Book> books = bookService.findAllBooksWithCopies();
        logger.trace("In BookController, method=getAllBooksWithCopies, books={}", books);
        return new ArrayList<>(bookWithCopiesConverter.convertModelsToDtos(books));
    }

    @RequestMapping(value = "/getOneBook", method = RequestMethod.GET)
    BookWithoutCopiesDTO getOneBook(@RequestParam Long id){
        Optional<Book> book = bookService.findOne(id);
        if(book.isEmpty()){
            return null;
        }

        return bookWithoutCopiesConverter.convertModelToDto(book.get());
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    BookWithoutCopiesDTO saveBook(@RequestBody BookWithoutCopiesDTO bookWithoutCopiesDTO){
        logger.trace("In BookController, method=saveBook, bookDTO={}", bookWithoutCopiesDTO);
        Book book = bookWithoutCopiesConverter.convertDtoToModel(bookWithoutCopiesDTO);

        Book savedBook = bookService.saveBook(book);
        logger.trace("In BookController, method=saveBook, savedBook={}", savedBook);
        return bookWithoutCopiesConverter.convertModelToDto(savedBook);
    }

    @RequestMapping(value = "/modifyBook", method = RequestMethod.POST)
    BookWithoutCopiesDTO modifyBook(@RequestBody BookWithoutCopiesDTO bookWithoutCopiesDTO){
        logger.trace("In BookController, method=modifyBook, bookDTO={}", bookWithoutCopiesDTO);
        Book book = bookWithoutCopiesConverter.convertDtoToModel(bookWithoutCopiesDTO);

        Optional<Book> updatedBook = bookService.modifyBook(book);
        if(updatedBook.isEmpty()){
            return null;
        }
        logger.trace("In BookController, method=modifyBook, updatedBook={}", updatedBook);
        return bookWithoutCopiesConverter.convertModelToDto(updatedBook.get());
    }

    @RequestMapping(value = "/removeBook/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeBook(@PathVariable Long id){
        logger.trace("In BookController, method=removeBook");
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rentBook", method = RequestMethod.POST)
    BookRentDTO rentBook(@RequestBody BookRentDTO bookRentDTO){
        BookRent bookRent = bookRentConverter.convertDtoToModel(bookRentDTO);

        Optional<BookRent> savedBookRent = bookService.saveBookRent(bookRent);
        if(savedBookRent.isEmpty()){
            return null;
        }
        return bookRentConverter.convertModelToDto(savedBookRent.get());
    }

    @RequestMapping(value = "/requestBookRent", method = RequestMethod.POST)
    RentRequestDTO requestBookRent(@RequestBody RentRequestDTO rentRequestDTO){
        RentRequest rentRequest = rentRequestConverter.convertDtoToModel(rentRequestDTO);

        Optional<RentRequest> savedRentRequest = bookService.saveRentRequest(rentRequest);
        if(savedRentRequest.isEmpty()){
            return null;
        }

        return rentRequestConverter.convertModelToDto(savedRentRequest.get());
    }

    @RequestMapping(value = "/requestBook", method = RequestMethod.POST)
    BookRequestDTO requestBook(@RequestBody BookRequestDTO bookRequestDTO){
        BookRequest bookRequest = bookRequestConverter.convertDtoToModel(bookRequestDTO);

        Optional<BookRequest> savedBookRequest = bookService.saveBookRequest(bookRequest);
        if(savedBookRequest.isEmpty()){
            return null;
        }

        return bookRequestConverter.convertModelToDto(savedBookRequest.get());
    }

    @RequestMapping(value = "/getAllBookRequests", method = RequestMethod.GET)
    List<BookRequestDTO> getAllBookRequests(){
        List<BookRequest> requests = bookService.getAllBookRequests();

        return bookRequestConverter.convertModelsToDtos(requests);
    }
}
