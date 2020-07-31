package arobs.library.core.service;

import arobs.library.core.model.Copy;
import arobs.library.core.repository.CopyJDBCRepository;
import arobs.library.core.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CopyServiceImpl extends CopyJDBCRepository implements CopyService {
    @Autowired
    private CopyRepository copyRepository;

    @Override
    public List<Copy> getAllCopies() {
        return copyRepository.findAllWithBook();
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

        copy.setCode(oldCopy.getCode());
        copy.setIsAvailable(oldCopy.getIsAvailable());
        copy.setStatus(oldCopy.getStatus());

        return Optional.of(copy);
    }

    @Override
    public List<Copy> getAllCopiesJDBC() {
        return super.getAllCopiesJDBC();
    }
}
