package hello;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by samford on 6/10/15.
 */
public class MessageController {

    List<Message> messages = new ArrayList<Message>();

    public MessageController() {
        Message m1 = new Message("Hello 1!", 1);
        Message m2 = new Message("Hello 2!", 1);
        Message m3 = new Message("Hello 3!", 2);
        m1.messageId = 1;
        m2.messageId = 2;
        m3.messageId = 3;
        messages.add(m1);
        messages.add(m2);
        messages.add(m3);

    }

    @RequestMapping(value="/messages/all")
    public List getMessages() {
        return messages;
    }

    @RequestMapping(method=RequestMethod.GET, value="/messages")
    public Message getMessage(@RequestParam("messageId") int messageId) {
        for (Message mess : messages) {
            if (mess.messageId == messageId) {
                return mess;
            }
        }
        return null;
    }

    @RequestMapping(method=RequestMethod.POST, value="/messages")
    public Message createMessage(@RequestParam("content") String content, @RequestParam("senderId") int senderId) {
        Message mess = new Message(content, senderId);
        messages.add(mess);
        return mess;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/messages")
    public boolean deleteMessage(@RequestParam("messageId") int messageId) {
        for (Message mess : messages) {
            if (mess.messageId == messageId) {
                return messages.remove(mess);
            }
        }
        return false;
    }
}
