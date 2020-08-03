package arobs.library.core.repository;

import arobs.library.core.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JPARepository<Book, Long> {
    @Query("select b from Book b")
    @EntityGraph(value = "bookWithCopies", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Book> findAllWithCopies();

    @Query("select b from Book b")
    List<Book> findAllWithoutCopies();

    @Query("select b from Book b where  b.id = :id")
    @EntityGraph(value = "bookWithCopies", type =
            EntityGraph.EntityGraphType.LOAD)
    Optional<Book> findOneWithCopies(@Param("id") Long id);

    Optional<Book> findBookById(@Param("id") Long id);

    Optional<Book> findBookByTitle(@Param("title") String title);

}
