package main.java.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

/**
 * Created by samford on 6/10/15.
 */
@RestController
public class UserController {

    List<User> users = new ArrayList<User>();

    public UserController() {
        User user1 = new User("Bob");
        User user2 = new User("John");
        User user3 = new User("Sally");
        users.add(user1);
        users.add(user2);
        users.add(user3);

    }

    @RequestMapping(value="/auth")
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

    @RequestMapping(method=RequestMethod.GET, value="/users/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        for (User user : users) {
            if (user.userId == userId) {
                return user;
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

