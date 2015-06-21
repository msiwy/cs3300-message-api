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

    public GroupController() {
        this.dao = new GroupDao();
    }

    @RequestMapping(method=RequestMethod.GET, value="/all")
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<Group>();
        groups = this.dao.getAllGroups();
        return groups;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{groupId}")
    public Group getGroup(@PathVariable("groupId") int groupId) {
        Group group = this.dao.getGroup(groupId);
        return group;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{groupId}/users")
    public Group getMembers(@PathVariable("groupId") int groupId) {
        return null;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{groupId}/messages")
    public Group getMessages(@PathVariable("groupId") int groupId) {
        return null;
    }


    @RequestMapping(method=RequestMethod.POST, value="/create")
    public Group createGroup(@RequestParam("groupname") String groupname) {
        return this.dao.create(groupname);
    }

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method=RequestMethod.POST, value="/add")
    public Group AddUser(@RequestParam("groupId") int groupId, @RequestParam("userId") int userId) {
        new GroupParticipant(counter.incrementAndGet(),groupId,userId);
        return this.getGroup(groupId);
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public boolean deleteGroups(@RequestParam("groupId") int groupId) {

        return false;
    }
}

