package restful;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by shauvik on 6/20/15.
 */
public class UserDao {


    public User getUserByName(String username) {
        String query = "SELECT * FROM User WHERE username = ?";
        Object [] args = {username};
        User user = RDS.getTemplate().queryForObject(query, args, new UserMapper());
        return user;
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM User";
        List<Map<String,Object>> rows = RDS.getTemplate().queryForList(SQL);
        List<User> users = new ArrayList<>();
        for (Map row : rows) {
            User user = new User((Integer)row.get("userId"), (String)row.get("username"));
            users.add(user);
        }
        return users;
    }

    public User getUser(int userId) {
        String query = "SELECT * FROM User WHERE userId = %d";
        String SQL = String.format(query,userId);
        User user = RDS.getTemplate().queryForObject(SQL, new UserMapper());
        return user;
    }

    public User create(String username) {
        Random rand = new Random(100);
        //int rn = rand.nextInt();
        int userId = (username).hashCode();
        String query = "INSERT INTO User (userId, username) VALUES (%d, '%s')";
        String SQL = String.format(query,userId,username);
        RDS.getTemplate().update(SQL);
        //System.out.println("Created Record Name = " + name + " Age = " + age);
        return new User(userId,username);
    }

    public User update(int userId, String username) {
        String SQL = "UPDATE User SET username = '%s' WHERE userId = %d";
        RDS.getTemplate().update(SQL, username, userId);
        return new User(userId, username);
    }

    public void delete(int userId) {
        String query = "DELETE FROM User WHERE userId = %d";
        String SQL = String.format(query,userId);
        RDS.getTemplate().update(SQL);
    }
}
