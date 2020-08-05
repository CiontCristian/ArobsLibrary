package arobs.library.core.service;

import arobs.library.core.model.Book;
import arobs.library.core.model.Copy;

import java.util.List;
import java.util.Optional;

public interface CopyService {
    List<Copy> getAllCopies();
    List<Copy> getAllAvailableCopies(Long bookID);
    List<Copy> getAllRentedCopies(Long bookID);
    Copy saveCopy(Copy copy);
    Optional<Copy> modifyCopy(Copy copy);
}
