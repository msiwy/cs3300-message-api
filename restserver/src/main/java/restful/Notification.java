package restful;

import java.sql.Timestamp;

/**
 * Created by magnussiwy on 6/22/15.
 */
public class Notification {

    private int userId;
    private int messageId;
    private int senderId;
    private int groupId;
    private Timestamp dateReceived;
    private boolean readState;
    private String content;


    public Notification(int userId, int messageId, int senderId, int groupId, Timestamp dateReceived, boolean readState, String content) {
        this.userId = userId;
        this.messageId = messageId;
        this.senderId = senderId;
        this.groupId = groupId;
        this.dateReceived = dateReceived;
        this.readState = readState;
        this.content = content;

    }
    public int getUserId() {
        return userId;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getGroupId() {
        return groupId;
    }

    public Timestamp getDateReceived() {
        return dateReceived;
    }

    public boolean isReadState() {
        return readState;
    }

    public String getContent() {
        return content;
    }
}
