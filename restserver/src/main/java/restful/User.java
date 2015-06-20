package restful;

/**
 * Created by samford on 6/10/15.
 */
public class User {
    private int userId;
    private String username;
    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return this.userId;
    }

    public String  getUsername() {
        return this.username;
    }
}
