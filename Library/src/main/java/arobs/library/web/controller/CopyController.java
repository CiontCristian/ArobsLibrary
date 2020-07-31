package arobs.library.web.controller;

import arobs.library.core.model.Copy;
import arobs.library.core.service.CopyService;
import arobs.library.core.service.CopyServiceImpl;
import arobs.library.web.converter.CopyConverter;
import arobs.library.web.dto.CopyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/copy")
public class CopyController {
    @Autowired
    private CopyServiceImpl copyService;

    @Autowired
    private CopyConverter copyConverter;

    @RequestMapping(value = "/getAllCopies", method = RequestMethod.GET)
    List<CopyDTO> getAllCopies(){
        List<Copy> copies = copyService.getAllCopies();

        return copyConverter.convertModelsToDtos(copies);
    }

    @RequestMapping(value = "/getAllCopiesJDBC", method = RequestMethod.GET)
    List<CopyDTO> getAllCopiesJDBC(){
        List<Copy> copies = copyService.getAllCopiesJDBC();

        return copyConverter.convertModelsToDtos(copies);
    }
}
