package restful;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by magnussiwy on 6/21/15.
 */
public class MessageDao {
    public List<Message> getAllMessages() {
        String SQL = "SELECT * FROM Message";
        List<Map<String,Object>> rows = RDS.getTemplate().queryForList(SQL);
        List<Message> messages = new ArrayList<Message>();
        for (Map row : rows) {
            Message message = new Message((Integer) row.get("messageId"), (Integer) row.get("senderId"), (Timestamp) row.get("dateCreated"),
                    (String) row.get("content"), (Integer) row.get("messageId"), (Integer) row.get("documentId"));
            messages.add(message);
        }
        return messages;
    }

    public Message getMessage(int messageId) {
        String query = "SELECT * FROM Message WHERE messageId = %d";
        String SQL = String.format(query,messageId);
        Message message = RDS.getTemplate().queryForObject(SQL, new MessageMapper());
        return message;
    }

    public Message create(int senderId, String content, int groupId, int documentId) {
        Random rand = new Random(100);
        int rn = rand.nextInt();
        int messageId = Math.abs((content + rn).hashCode());
        Timestamp dateCreated = new Timestamp(System.currentTimeMillis() / 1000);
        String query = "INSERT INTO Message VALUES (%d,%d,'%s',%p,'%s',%d,%d)";
        String SQL = String.format(query,messageId,senderId,dateCreated,content,groupId,documentId);
        RDS.getTemplate().update(SQL);
        return new Message(messageId,senderId,dateCreated,content,groupId,documentId);
    }

    public List<Message> getMessageByMessageId(String messageId) { // return in order by time
        String query = "SELECT * FROM Message WHERE messageId = %d";
        String SQL = String.format(query,messageId);
        List<Map<String,Object>> rows = RDS.getTemplate().queryForList(SQL);
        List<Message> messages = new ArrayList<Message>();
        for (Map row : rows) {
            Message message = new Message((Integer) row.get("messageId"), (Integer) row.get("senderId"), (Timestamp) row.get("dateCreated"),
                    (String) row.get("content"), (Integer) row.get("messageId"), (Integer) row.get("documentId"));
            messages.add(message);
        }
        return messages;
    }

    public void delete(int messageId){
        String query = "DELETE FROM Message WHERE messageId = %d";
        String SQL = String.format(query,messageId);
        RDS.getTemplate().update(SQL);
    }
}
