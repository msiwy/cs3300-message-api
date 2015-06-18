package main.java.restful;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by gatsby on 6/16/15.
 */
public class MessageJDBCTemplate {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Message create(int senderId, String content, int groupId, int documentId) { // not if one exists
        Random rand = new Random(100);
        int rn = rand.nextInt();
        int messageId = Math.abs((content + rn).hashCode());
        Timestamp dateCreated = new Timestamp(System.currentTimeMillis() / 1000);
        String query = "INSERT INTO Message VALUES (%d,%d,'%s',%p,'%s',%d,%d)";
        String SQL = String.format(query,messageId,senderId,dateCreated,content,groupId,documentId);
        jdbcTemplateObject.update(SQL);
        return new Message(messageId,senderId,dateCreated,content,groupId,documentId);
    }

    public Message getMessage(int messageId) {
        String query = "SELECT * FROM Message WHERE messageId = %d";
        String SQL = String.format(query,messageId);
       Message message = jdbcTemplateObject.queryForObject(SQL, new MessageMapper());
        return message;
    }

    public List<Message> getMessageByMessage(String messageId) { // return in order by time
        String query = "SELECT * FROM Message WHERE messageId = %d";
        String SQL = String.format(query,messageId);
        List<Map<String,Object>> rows = jdbcTemplateObject.queryForList(SQL);
        List<Message> messages = new ArrayList<>();
        for (Map row : rows) {
                Message message = new Message((Integer) row.get("messageId"), (Integer) row.get("senderId"), (Timestamp) row.get("dateCreated"),
                        (String) row.get("content"), (Integer) row.get("messageId"), (Integer) row.get("documentId"));
                messages.add(message);
        }
        return messages;
    }

    public List<Message> getAllMessages() {
        String SQL = "SELECT * FROM Messages";
        List<Map<String,Object>> rows = jdbcTemplateObject.queryForList(SQL);
        List<Message> messages = new ArrayList<>();
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
        jdbcTemplateObject.update(SQL);
    }

}
