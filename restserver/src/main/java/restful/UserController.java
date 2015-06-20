package restful;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by samford and yuna on 07/15/2015
 */
@RestController
@RequestMapping(value="/users")
public class UserController {
    UserDao dao;

    public UserController() {
        this.dao = new UserDao();
    }

    @RequestMapping(value="/auth")
    public User auth(@RequestParam("username") String username) {
        User user = this.dao.getUserByName(username);
        return user;
    }

    @RequestMapping(value="/all")
    public List<User> getAllUsers() {
        List<User> users = this.dao.getAllUsers();
        return users;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        User user = this.dao.getUser(userId);
        return user;
    }

    @RequestMapping(method=RequestMethod.POST)
    public User createUser(@RequestParam("username") String username) {
        User user = this.dao.create(username);
        return user;
    }

    @RequestMapping(method=RequestMethod.POST, value="/{userId}/update")
    public User updateUser(@PathVariable("userId") int userId, @RequestParam("username") String username) {
        User user = this.dao.update(userId, username);
        return user;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public Status deleteUser(@RequestParam("userId") int userId) {
        try {
            this.dao.delete(userId);
            return new Status(true);
        }
        catch(Exception e) {
            return new Status(false);
        }
    }
}

