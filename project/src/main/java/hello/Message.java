package hello;

import java.util.Date;

/**
 * Created by samford on 6/10/15.
 */
public class Message {
//    messageId: 100, dateCreated : 34623754762354, content : "Hey Doug", senderId : 1234
    public int messageId;
    public long dateCreated;
    public String content;
    public int senderId;
    public Message(String content, int senderId) {
        this.content = content;
        this.senderId = senderId;
        Date date = new Date();
        this.dateCreated = date.getTime();
        this.messageId = date.hashCode();
        this.messageId = 0;
    }
}
