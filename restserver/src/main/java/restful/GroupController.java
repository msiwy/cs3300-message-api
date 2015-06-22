package restful;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Gatsby on 6/10/15.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {
    GroupDao dao;
    private final AtomicLong counter = new AtomicLong();

    public GroupController() {
        this.dao = new GroupDao();
    }

    /**
     * Status - Complete
     */
    @RequestMapping(method=RequestMethod.GET, value="/all")
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<Group>();
        groups = this.dao.getAllGroups();
        return groups;
    }

    /**
     * Status - Complete
     * TODO - PRIORITY LOW - Add to README that 'null' will be returned if the group does not have a name
     */
    @RequestMapping(method=RequestMethod.GET, value="/{groupId}")
    public Group getGroup(@PathVariable("groupId") int groupId) {
        Group group = this.dao.getGroup(groupId);
        return group;
    }

    /**
     * Status - Not implemented
     * TODO - PRIORITY VERY HIGH - Implement
     * Example:
     * [
     *      {"userId" : 1234, "username" : "Ted"},
     *      {"userId" : 1235, "username" : "Bill"},
     *      {...}
     * ]
     */
    @RequestMapping(method=RequestMethod.GET, value="/{groupId}/users")
    public Group getMembers(@PathVariable("groupId") int groupId) {
        return null;
    }

    /**
     * Status - Not implemented
     * TODO - PRIORITY VERY HIGH - Implement
     * Example:
     * [
     *      { "messageId": 100, "senderId" : 1000, "dateCreated" : 34623754762354, "content" : "Hey Doug." },
     *      { "messageId": 101, "senderId" : 3295, "dateCreated" : 34623754762378, "content" : "Hey Ted, what is up." },
     *      { ... }
     * ]
     */
    @RequestMapping(method=RequestMethod.GET, value="/{groupId}/messages")
    public Group getMessages(@PathVariable("groupId") int groupId) {
        return null;
    }

    /**
     * Status - Working
     * TODO - PRIORITY HIGH - Allow names to be in quotes and format the input values properly into DB (IE : not adding "" or / to DB)
     */
    @RequestMapping(method=RequestMethod.POST, value="/create")
    public Group createGroup(@RequestParam("groupname") String groupname) {
        return this.dao.create(groupname);
    }


    /**
     * Status - Unchecked
     */
    @RequestMapping(method=RequestMethod.POST, value="/add")
    public Group AddUser(@RequestParam("groupId") int groupId, @RequestParam("userId") int userId) {
        new GroupParticipant(counter.incrementAndGet(),groupId,userId);
        return this.getGroup(groupId);
    }

    /**
     * Status - Unchecked
     */
    @RequestMapping(method=RequestMethod.DELETE)
    public boolean deleteGroups(@RequestParam("groupId") int groupId) {

        return false;
    }
}

