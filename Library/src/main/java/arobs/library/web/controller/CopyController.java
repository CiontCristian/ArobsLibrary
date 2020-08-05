package arobs.library.web.controller;

import arobs.library.core.model.Book;
import arobs.library.core.model.Copy;
import arobs.library.core.service.CopyService;
import arobs.library.core.service.CopyServiceImpl;
import arobs.library.web.converter.BookWithoutCopiesConverter;
import arobs.library.web.converter.CopyConverter;
import arobs.library.web.dto.BookWithoutCopiesDTO;
import arobs.library.web.dto.CopyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/copy")
public class CopyController {
    public static final Logger logger = LoggerFactory.getLogger(CopyController.class);
    @Autowired
    private CopyServiceImpl copyService;

    @Autowired
    private CopyConverter copyConverter;
    @Autowired
    private BookWithoutCopiesConverter bookWithoutCopiesConverter;

    @RequestMapping(value = "/getAllCopies", method = RequestMethod.GET)
    List<CopyDTO> getAllCopies(){
        List<Copy> copies = copyService.getAllCopies();

        return copyConverter.convertModelsToDtos(copies);
    }

    @RequestMapping(value = "/getAllAvailableCopies", method = RequestMethod.POST)
    List<CopyDTO> getAllAvailableCopies(@RequestBody Long bookID){
        List<Copy> copies = copyService.getAllAvailableCopies(bookID);
        logger.trace("In CopyController, method=getAllAvailableCopies, copies={}", copies);

        return copyConverter.convertModelsToDtos(copies);
    }

    @RequestMapping(value = "/getAllRentedCopies", method = RequestMethod.POST)
    List<CopyDTO> getAllRentedCopies(@RequestBody Long bookID){
        List<Copy> copies = copyService.getAllRentedCopies(bookID);
        logger.trace("In CopyController, method=getAllRentedCopies, copies={}", copies);

        return copyConverter.convertModelsToDtos(copies);
    }

    @RequestMapping(value = "/getAllCopiesJDBC", method = RequestMethod.GET)
    List<CopyDTO> getAllCopiesJDBC(){
        List<Copy> copies = copyService.getAllCopiesJDBC();

        return copyConverter.convertModelsToDtos(copies);
    }

    @RequestMapping(value = "/modifyCopy", method = RequestMethod.PUT)
    CopyDTO modifyCopy(CopyDTO copyDTO){
        Copy copy = copyConverter.convertDtoToModel(copyDTO);

        Optional<Copy> modifiedCopy = copyService.modifyCopy(copy);
        if(modifiedCopy.isEmpty()){
            return null;
        }

        return copyConverter.convertModelToDto(modifiedCopy.get());
    }
}
