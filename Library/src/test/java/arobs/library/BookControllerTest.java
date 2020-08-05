package arobs.library;

import arobs.library.core.model.Book;
import arobs.library.web.dto.BookWithoutCopiesDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void getAllBooks(){
        ResponseEntity<BookWithoutCopiesDTO[]> responseEntity =
                testRestTemplate.getForEntity("/book/getAllBooksWithoutCopies", BookWithoutCopiesDTO[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).length);
    }

    @Test
    void getOneBook(){
        ResponseEntity<BookWithoutCopiesDTO> responseEntity =
                testRestTemplate.getForEntity("/book/getOneBook?id=2", BookWithoutCopiesDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Steve", Objects.requireNonNull(responseEntity.getBody()).getAuthor());
    }

    @Test
    void saveAndDeleteBook(){
        BookWithoutCopiesDTO book1 = new BookWithoutCopiesDTO("Title1", "Auth1", "Desc1", "Tag1", new Date());

        ResponseEntity<BookWithoutCopiesDTO> responseEntity =
                testRestTemplate.postForEntity("/book/saveBook", book1, BookWithoutCopiesDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Auth1", Objects.requireNonNull(responseEntity.getBody()).getAuthor());

        ResponseEntity<BookWithoutCopiesDTO[]> responseEntity2 =
                testRestTemplate.getForEntity("/book/getAllBooksWithoutCopies", BookWithoutCopiesDTO[].class);
        assertEquals(4, Objects.requireNonNull(responseEntity2.getBody()).length);

        String deleteURL = "/book/removeBook/" + responseEntity.getBody().getId();
        System.out.println(deleteURL);
        testRestTemplate.delete(deleteURL);

        ResponseEntity<BookWithoutCopiesDTO[]> responseEntity3 =
                testRestTemplate.getForEntity("/book/getAllBooksWithoutCopies", BookWithoutCopiesDTO[].class);
        assertEquals(3, Objects.requireNonNull(responseEntity3.getBody()).length);
    }
}
