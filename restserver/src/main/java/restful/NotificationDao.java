package restful;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by magnussiwy on 6/24/15.
 */
public class NotificationDao {
    GroupDao groupDao = new GroupDao();

    public List<Notification> getNotifications(int userId, String type) {
        String query;
        String SQL = "";
        if (type.equals("unread")) {
            // Get all unread notifications
            query = "SELECT * FROM Notification WHERE userId = %d AND readState = %s";
            SQL = String.format(query, userId, "false");
        } else if (type.equals("all")) {
            query = "SELECT * FROM Notification WHERE userId = %d";
            SQL = String.format(query, userId, "false");
        } else {
            System.err.println("Unknown Notification type requested");
            return null; // My horrible error handling, change this
        }

        // Get list of unread Notifications
        List<Notification> notifications = RDS.getTemplate().query(SQL, new NotificationMapper());

        // Update all the user's Notifications to readState = true
        query = "UPDATE Notification SET readState = %s WHERE userId = %d";
        SQL = String.format(query, "true", userId);
        RDS.getTemplate().update(SQL);

        return notifications;
    }

    /**
     * TODO - change now() to message.getDateCreated in query and make it work
     */
    public void generateNotifications(Message message) {
        List<User> usersWithNotifications = groupDao.getMembers(message.getGroupId());

        // Remove sender from usersWithNotifications list
        User sender = null;
        for (User u : usersWithNotifications) {
            if (u.getUserId() == message.getSenderId()) {
                sender = u;
            }
        }
        usersWithNotifications.remove(sender);

        // readState defaults to false (unread)
        String query = "INSERT INTO Notification (userId, messageId, senderId, groupId, dateReceived, content) " +
                       "VALUES (%d, %d, %d, %d, now(), %s)";
        String SQL = "";
        for (User u : usersWithNotifications) {
            SQL = String.format(query, u.getUserId(), message.getMessageId(), message.getSenderId(), message.getGroupId(),
                     message.getContent());
            RDS.getTemplate().update(SQL);
        }
    }
}
