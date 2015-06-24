package restful;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by magnussiwy on 6/21/15.
 */
@RestController
@RequestMapping("/messages")
public class MessageController {
    MessageDao dao;

    public MessageController() {
        this.dao = new MessageDao();
    }

    /**
     * Function currently not needed - not listed in our documentation
     */
//    @RequestMapping(value="/all")
    public List<Message> getMessages() {
        return this.dao.getAllMessages();
    }

    /**
     * Function currently not needed - not listed in our documentation
     */
//    @RequestMapping(method= RequestMethod.GET,value="/{messageId}")
    public Message getMessage(@PathVariable("messageId") int messageId) {
        return this.dao.getMessage(messageId);
    }

    /**
     * Status - Working
     * TODO - PRIORITY LOW - Fix documentId - @RequestParam(value = "documentId", required = false) int documentId
     */
    @RequestMapping(method=RequestMethod.POST)
    public Message sendMessage(@RequestParam("senderId") int senderId,
                                 @RequestParam("recipientIds") String[] recipientIds,
                                 @RequestParam("content") String content) {
        return this.dao.sendMessage(senderId, recipientIds, content);
    }
}
