package restful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by samford on 6/24/15.
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {
    DocumentDao dao;

    public DocumentController() {
        this.dao = new DocumentDao();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Document getDocument(@RequestParam("documentId") int documentId) {
        return this.dao.getDocument(documentId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Document addDocument(@RequestParam("name") String name, @RequestParam("type") String type) {
        return this.dao.create(name, type);
    }


}

