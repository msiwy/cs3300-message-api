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

    /**
     * STATUS - Working
     * TODO
     */
    @RequestMapping(value="/auth")
    public User auth(@RequestParam("username") String username) {
        User user = this.dao.getUserByName(username);
        return user;
    }

    /**
     * STATUS - Working
     * TODO - PRIORITY LOW - Only minor change would be to have the function return username first, then userId to match README
     */
    @RequestMapping(value="/all")
    public List<User> getAllUsers() {
        List<User> users = this.dao.getAllUsers();
        return users;
    }

    /**
     * Status - Working
     * TODO - PRIORITY MEDIUM - Need response message for userId that does not exist
     */
    @RequestMapping(method=RequestMethod.GET, value="/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        User user = this.dao.getUser(userId);
        return user;
    }

    /**
     * Status - Working
     * TODO - PRIORITY HIGH - Check if userFormat (in UserDao) method is working correctly, then port to groups and messages (IE : not adding "" or / to DB)
     */
    @RequestMapping(method=RequestMethod.POST)
    public User createUser(@RequestParam("username") String username) {
        User user = this.dao.create(username);
        return user;
    }

    /**
     * Status - Not working
     * TODO - PRIORITY MEDIUM - Fix SQL Error
     */
    @RequestMapping(method=RequestMethod.POST, value="/{userId}/update")
    public User updateUser(@PathVariable("userId") int userId, @RequestParam("username") String username) {
        User user = this.dao.update(userId, username);
        return user;
    }

    /**
     *  Status - Implemented
     *  TODO - Check if return values comply with readme
     */
    @RequestMapping(method=RequestMethod.GET, value="/{userId}/groups")
    public List<Group> getUserGroups(@PathVariable("userId") int userId) {
        List<Group> groups = this.dao.getUserGroups(userId);
        return groups;
    }

    /**
     * Status - Unchecked
     */
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

