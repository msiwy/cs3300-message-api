package restful;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by magnussiwy on 6/24/15.
 */
public class NotificationMapper implements RowMapper<Notification> {
    public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
        boolean readState;

        if (rs.getString("readState").equals("false")) {
            readState = false;
        } else {
            readState = true;
        }

        return new Notification(rs.getInt("userId"),
                                rs.getInt("messageId"),
                                rs.getInt("senderId"),
                                rs.getInt("groupId"),
                                rs.getTimestamp("dateReceived"),
                                readState,
                                rs.getString("content"));
    }
}
