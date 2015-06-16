package main.java.restful;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by samford and yuna on 07/15/2015
 */
@RestController
@RequestMapping(value="/users")
public class UserController {

    private UserJDBCTemplate userJDBCTemplate;

    public UserController() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
        this.userJDBCTemplate =
                (UserJDBCTemplate)context.getBean("userJDBCTemplate");
    }

    @RequestMapping(value="/auth")
    public User auth(@RequestParam("username") String username) {
        User user = this.userJDBCTemplate.getUserByName(username);
        return user;
    }

    @RequestMapping(value="/all")
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        users = this.userJDBCTemplate.getAllUsers();
        return users;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        User user = this.userJDBCTemplate.getUser(userId);
        return user;
    }

    @RequestMapping(method=RequestMethod.POST)
    public User createUser(@RequestParam("username") String username) {
        User user = this.userJDBCTemplate.create(username);
        return user;
    }

    @RequestMapping(method=RequestMethod.POST, value="/{userId}/update")
    public User updateUser(@PathVariable("userId") int userId, @RequestParam("username") String username) {
        User user = this.userJDBCTemplate.update(userId, username);
        return user;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public Status deleteUser(@RequestParam("userId") int userId) {
        try {
            this.userJDBCTemplate.delete(userId);
            return new Status(true);
        }
        catch(Exception e) {
            return new Status(false);
        }
    }
}

