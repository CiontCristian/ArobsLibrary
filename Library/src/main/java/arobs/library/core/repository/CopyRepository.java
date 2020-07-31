package arobs.library.core.repository;

import arobs.library.core.model.Book;
import arobs.library.core.model.Copy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CopyRepository extends JPARepository<Copy, Long> {
    @Query("select c from Copy c")
    @EntityGraph(value = "copyWithBook", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Copy> findAllWithBook();
}
