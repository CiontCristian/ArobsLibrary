package arobs.library.core.repository;

import arobs.library.core.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JPARepository<Book, Long> {
    @Query("select b from Book b")
    @EntityGraph(value = "bookWithCopies", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Book> findAllWithCopies();

    @Query("select b from Book b")
    List<Book> findAllWithoutCopies();

}
