package restful;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by magnussiwy on 6/21/15.
 */
public class MessageController {
    MessageDao dao;

    public MessageController() {
        this.dao = new MessageDao();
    }

    @RequestMapping(value="/all")
    public List<Message> getMessages() {
        return this.dao.getAllMessages();
    }

    @RequestMapping(method= RequestMethod.GET,value="/{messageId}")
    public Message getMessage(@PathVariable("messageId") int messageId) {
        return this.dao.getMessage(messageId);
    }


    @RequestMapping(method=RequestMethod.POST)
    public Message createMessage(@RequestParam("senderId") int senderId, @RequestParam("content") String content, @RequestParam("groupId") int groupId, @RequestParam("documentId") int documentId) {
        return this.dao.create(senderId,content,groupId,documentId);
    }
    /*
    @RequestMapping(method=RequestMethod.DELETE, value="/messages")
    public boolean deleteMessage(@RequestParam("messageId") int messageId) {
        for (Message mess : messages) {
            if (mess.messageId == messageId) {
                return messages.remove(mess);
            }
        }
        return false;
    }
    */
}
