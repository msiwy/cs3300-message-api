package restful;

/**
 * Created by gatsby on 6/15/15.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJDBCTemplate {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public User create(String username) { // not if one exists
        Random rand = new Random(100);
        //int rn = rand.nextInt();
        int userId = (username).hashCode();
        String query = "INSERT INTO User (userId, username) VALUES (%d, '%s')";
        String SQL = String.format(query,userId,username);
        jdbcTemplateObject.update(SQL);
        //System.out.println("Created Record Name = " + name + " Age = " + age);
        return new User(userId,username);
    }

    public User getUser(int userId) {
        String query = "SELECT * FROM User WHERE userId = %d";
        String SQL = String.format(query,userId);
        User user = jdbcTemplateObject.queryForObject(SQL, new UserMapper());
        return user;
    }

    public User getUserByName(String username) {
        String query = "SELECT * FROM User WHERE username = '%s'";
        String SQL = String.format(query,username);
        User user = jdbcTemplateObject.queryForObject(SQL, new UserMapper());
        return user;
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM User";
        List<Map<String,Object>> rows = jdbcTemplateObject.queryForList(SQL);
        List<User> users = new ArrayList<>();
        for (Map row : rows) {
            User user = new User((Integer)row.get("userId"), (String)row.get("username"));
            users.add(user);
        }
        return users;
    }

    public void delete(int userId){
        String query = "DELETE FROM User WHERE userId = %d";
        String SQL = String.format(query,userId);
        jdbcTemplateObject.update(SQL);
    }

    public User update(int userId, String username){
        String SQL = "UPDATE User SET username = '%s' WHERE userId = %d";
        jdbcTemplateObject.update(SQL, username, userId);
        return new User(userId, username);
    }

}
