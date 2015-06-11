package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by samford on 6/10/15.
 */
@RestController
public class UsersController {

    List<User> users = new ArrayList<User>();

    public UsersController() {
        User user1 = new User("Bob");
        User user2 = new User("John");
        User user3 = new User("Sally");
        user1.userId = 1;
        user2.userId = 2;
        user3.userId = 3;
        users.add(user1);
        users.add(user2);
        users.add(user3);

    }

    @RequestMapping("/auth")
    public int auth(@RequestParam("username") String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user.userId;
            }
        }
        return -1;
    }

    @RequestMapping(value="/users/all")
    public List getUsers() {
        return users;
    }

    @RequestMapping(method=RequestMethod.GET, value="/users")
    public User getUser(@RequestParam("userId") int userId) {
        for (User user : users) {
            if (user.userId == userId) {
                return user;
            }
            else if (user.userId == 0){
                return new User("hi");
            }
        }
        return null;
    }

    @RequestMapping(method=RequestMethod.POST, value="/users")
    public User createUser(@RequestParam("username") String username) {
        User user = new User(username);
        users.add(user);
        return user;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/users")
    public boolean deleteUser(@RequestParam("userId") int userId) {
        for (User user : users) {
            if (user.userId == userId) {
                return users.remove(user);
            }
        }
        return false;
    }
}
