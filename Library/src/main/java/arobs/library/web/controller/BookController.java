package arobs.library.web.controller;

import arobs.library.core.model.Book;
import arobs.library.core.service.BookService;
import arobs.library.web.converter.BookWithCopiesConverter;
import arobs.library.web.converter.BookWithoutCopiesConverter;
import arobs.library.web.dto.BookWithCopiesDTO;
import arobs.library.web.dto.BookWithoutCopiesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    BookWithoutCopiesDTO saveBook(@RequestBody BookWithoutCopiesDTO bookWithoutCopiesDTO){
        logger.trace("In BookController, method=saveBook, bookDTO={}", bookWithoutCopiesDTO);
        Book book = bookWithoutCopiesConverter.convertDtoToModel(bookWithoutCopiesDTO);

        Book savedBook = bookService.saveBook(book);
        logger.trace("In BookController, method=saveBook, savedBook={}", savedBook);
        return bookWithoutCopiesConverter.convertModelToDto(savedBook);
    }
}
