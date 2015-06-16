package main.java.restful;

/**
 * Created by gatsby on 6/15/15.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getInt("userId"),rs.getString("username"));
        return user;
    }
}