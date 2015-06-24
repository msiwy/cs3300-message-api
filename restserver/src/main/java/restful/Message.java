package restful;

import java.sql.Timestamp;

/**
 * Created by samford on 6/10/15.
 */
public class Message {
    private int messageId;
    private int senderId;
    private Timestamp dateCreated;
    private String content;
    private int groupId;
    private int documentId;

    public Message(int messageId, int senderId, Timestamp dateCreated, String content, int groupId, int documentId) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.dateCreated = dateCreated;
        this.content = content;
        this.groupId = groupId;
        this.documentId = 84783494;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public int getSenderId() {
        return this.senderId;

    }

    public Timestamp getDateCreated() {
        return this.dateCreated;
    }

    public String getContent() {
        return this.content;
    }

    public int getGroupId() {
        return this.groupId;
    }
}
