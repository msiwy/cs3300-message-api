package main.java.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Gatsby on 6/10/15.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    List<Group> groups = new ArrayList<Group>();

    public GroupController() {
        Group group1 = new Group("Klaus");
        Group group2 = new Group("SW");
        Group group3 = new Group("HW");
        group1.groupId = 1;
        group2.groupId = 2;
        group3.groupId = 3;
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);

    }

    @RequestMapping(value="/all")
    public List getAllGroups() {
        return groups;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{groupId}")
    public Group getGroups(@PathVariable("groupId") int groupId) {
        for (Group group : groups) {
            if (group.groupId == groupId) {
                return group;
            }
        }
        return null;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{groupId}/users")
    public Group getMembers(@PathVariable("groupId") int groupId) {
        for (Group group : groups) {
            if (group.groupId == groupId) {
                return group;
            }
        }
        return null;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{groupId}/messages")
    public Group getMessages(@PathVariable("groupId") int groupId) {
        return null;
    }


    @RequestMapping(method=RequestMethod.POST, value="/create")
    public Group createGroup(@RequestParam("groupname") String groupname) {
        Group group = new Group(groupname);
        groups.add(group);
        return group;
    }

    @RequestMapping(method=RequestMethod.POST, value="/add")
    public Group AddUser(@RequestParam("groupId") int groupId, @RequestParam("userId") int userId) {
        GroupParticipant gp = new GroupParticipant(groupId,userId);
        return null;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public boolean deleteGroups(@RequestParam("groupId") int groupId) {
        for (Group group : groups) {
            if (group.groupId == groupId) {
                return groups.remove(group);
            }
        }
        return false;
    }
}

