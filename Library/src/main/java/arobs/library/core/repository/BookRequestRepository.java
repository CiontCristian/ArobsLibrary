package arobs.library.core.repository;

import arobs.library.core.model.BookRequest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRequestRepository extends JPARepository<BookRequest, Long> {

    @Query("select br from BookRequest br")
    List<BookRequest> findAllWithoutEmployee();
}
