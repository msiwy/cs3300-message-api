package main.java.restful;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
>>>>>>> Stashed changes

/**
 * Created by samford and yunalee on 6/16/15.
 */
@RestController
@RequestMapping(value="/messages")
public class MessageController {

    private MessageJDBCTemplate msgJDBCTemplate;

    public MessageController() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
<<<<<<< Updated upstream
        //this.msgJDBCTemplate =
              //  (MessageJDBCTemplate)context.getBean("messageJDBCTemplate");
    }

    @RequestMapping(value="/all")
=======
        this.msgJDBCTemplate =
                (MessageJDBCTemplate)context.getBean("groupJDBCTemplate");
    }

    @RequestMapping(value="/messages/all")
>>>>>>> Stashed changes
    public List<Message> getMessages() {
        return this.msgJDBCTemplate.getAllMessages();
    }

    @RequestMapping(method=RequestMethod.GET)
    public Message getMessage(@RequestParam("messageId") int messageId) {
        return this.msgJDBCTemplate.getMessage(messageId);
    }

<<<<<<< Updated upstream
    @RequestMapping(method=RequestMethod.POST)
=======
    @RequestMapping(method=RequestMethod.POST, value="/messages")
>>>>>>> Stashed changes
    public Message createMessage(@RequestParam("senderId") int senderId, @RequestParam("content") String content, @RequestParam("groupId") int groupId, @RequestParam("documentId") int documentId) {
        return this.msgJDBCTemplate.create(senderId,content,groupId,documentId);
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
