package arobs.library.core.service;

import arobs.library.core.model.Book;
import arobs.library.core.model.Copy;
import arobs.library.core.repository.CopyJDBCRepository;
import arobs.library.core.repository.CopyRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CopyServiceImpl extends CopyJDBCRepository implements CopyService {
    @Autowired
    private CopyRepository copyRepository;

    @Override
    public List<Copy> getAllCopies() {
        return copyRepository.findAllWithBook();
    }

    @Override
    public List<Copy> getAllAvailableCopies(Long bookID) {
        return copyRepository.findAllWithBook()
                .stream()
                .filter(copy -> copy.getBook().getId().equals(bookID))
                .filter(copy -> copy.getStatus().equals("Available") && copy.getIsAvailable())
                .collect(Collectors.toList());
    }

    @Override
    public List<Copy> getAllRentedCopies(Long bookID) {
        return copyRepository.findAllWithBook()
                .stream()
                .filter(copy -> copy.getBook().getId().equals(bookID))
                .filter(copy -> copy.getStatus().equals("Rented"))
                .collect(Collectors.toList());
    }

    @Override
    public Copy saveCopy(Copy copy) {
        return copyRepository.save(copy);
    }

    @Override
    @Transactional
    public Optional<Copy> modifyCopy(Copy copy) {
        Copy oldCopy = copyRepository.findById(copy.getId()).orElse(null);

        if(oldCopy == null){
            return Optional.empty();
        }

        oldCopy.setStatus(copy.getStatus());
        oldCopy.setIsAvailable(copy.getIsAvailable());

        return Optional.of(oldCopy);
    }

    @Override
    public List<Copy> getAllCopiesJDBC() {
        return super.getAllCopiesJDBC();
    }
}
