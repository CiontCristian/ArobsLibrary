package arobs.library;

import arobs.library.core.model.Book;
import arobs.library.core.repository.BookRepository;
import arobs.library.core.service.BookService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void testFindAllBooks() {
        assertEquals(2, bookService.findAllBooksWithoutCopies().size());
    }

    @Test
    void testSaveAndDeleteBook(){
        Book book1 = new Book("Title1", "Auth1", "Desc1", "Tag1", new Date(), null);
        Book book2 = new Book("Title2", "Auth2", "Desc2", "Tag2", new Date(), null);

        bookService.saveBook(book1);
        bookService.saveBook(book2);

        assertEquals(5, bookService.findAllBooksWithoutCopies().size());

        bookService.deleteBook(book2.getId());

        assertEquals(4, bookService.findAllBooksWithoutCopies().size());

        bookService.deleteBook(book1.getId());
    }
}
