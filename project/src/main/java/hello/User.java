package hello;

/**
 * Created by samford on 6/10/15.
 */
public class User {
    public String username;
    public int userId
    public User(String username) {
        this.username = username;
        this.userId = username.hashCode();
    }
}
