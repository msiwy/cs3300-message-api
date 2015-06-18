package main.java.restful;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gatsby on 6/16/15.
 */
public class MessageMapper implements RowMapper<Message>{
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message(rs.getInt("messageId"),rs.getInt("senderId"),rs.getTimestamp("dateCreated"),rs.getString("content"),rs.getInt("groupId"),rs.getInt("documentId"));
        return message;
    }
}
