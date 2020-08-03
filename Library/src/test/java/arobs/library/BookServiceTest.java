package arobs.library;

import arobs.library.core.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void testFindAllBooks() {
        assertEquals(3, bookService.findAllBooksWithoutCopies().size());
    }
}
