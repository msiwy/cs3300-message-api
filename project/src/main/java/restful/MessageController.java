package main.java.restful;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

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
        //this.msgJDBCTemplate =
              //  (MessageJDBCTemplate)context.getBean("messageJDBCTemplate");
    }

    @RequestMapping(value="/all")
    public List<Message> getMessages() {
        return this.msgJDBCTemplate.getAllMessages();
    }

    @RequestMapping(method=RequestMethod.GET)
    public Message getMessage(@RequestParam("messageId") int messageId) {
        return this.msgJDBCTemplate.getMessage(messageId);
    }

    @RequestMapping(method=RequestMethod.POST)
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
