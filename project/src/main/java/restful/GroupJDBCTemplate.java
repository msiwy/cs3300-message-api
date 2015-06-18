package main.java.restful;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by gatsby on 6/15/15.
 */
import java.lang.Integer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by gatsby on 6/16/15.
 */
public class GroupJDBCTemplate {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Group create(String groupname) { // not if one exists
        Random rand = new Random(100);
        //int rn = rand.nextInt();
        int groupId = (groupName).hashCode();
        String query = "INSERT INTO Group (groupId, groupName) VALUES (%d, '%s')";
        String SQL = String.format(query,groupId,groupName);
        jdbcTemplateObject.update(SQL);
        //System.out.println("Created Record Name = " + name + " Age = " + age);
        return new Group(groupId,groupName);
    }

    public Group getGroup(int groupId) {
        String query = "SELECT * FROM Group WHERE groupId = %d";
        String SQL = String.format(query, groupId);
        Group Group = jdbcTemplateObject.queryForObject(SQL, new GroupMapper());
        return Group;
    }

    public List<Group> getAllGroups() {
        String SQL = "SELECT * FROM Group";
        List<Map<String,Object>> rows = jdbcTemplateObject.queryForList(SQL);
        List<User> groups = new ArrayList<>();
        for (Map row : rows) {
            Group group = new Group((Integer) row.get("groupId"), (String) row.get("groupName"));
        }
        int rn = rand.nextInt();
        int groupId = Math.abs((groupname + rn).hashCode());
        String query = "INSERT INTO `Group` (groupId, groupname) VALUES (%d, '%s')";
        String SQL = String.format(query,groupId,groupname);
        jdbcTemplateObject.update(SQL);
        //System.out.println("Created Record Name = " + name + " Age = " + age);
        return new Group(groupId,groupname);
    }

    public Group getGroup(int groupId) {
        String query = "SELECT * FROM `Group` WHERE groupId = %d";
        String SQL = String.format(query,groupId);
        Group group = jdbcTemplateObject.queryForObject(SQL, new GroupMapper());
        return group;
    }

    public Group getGroupByName(String groupname) {
        String query = "SELECT * FROM `Group` WHERE groupname = '%s'";
        String SQL = String.format(query,groupname);
        Group group = jdbcTemplateObject.queryForObject(SQL, new GroupMapper());
        return group;
    }

    public List<Group> getAllGroups() {
        String SQL = "Select * FROM `Group`";
        List<Map<String,Object>> rows = jdbcTemplateObject.queryForList(SQL);
        List<Group> groups = new ArrayList<>();
        for (Map row : rows) {
            Group group = new Group((Integer)row.get("groupId"), (String)row.get("groupname"));
            groups.add(group);
        }
        return groups;
    }

    public List<User> getAllUsersInGroup(int groupId) {
        String query = "SELECT * FROM GroupParticipant JOIN Users ON (GroupParticipant.userId = Users.userId) WHERE groupId = %d";
        String SQL = String.format(query, groupId);
        List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(SQL);
        List<User> users = new ArrayList<>();
        for (int id : userIds) {
            User user = new User((Integer)row.get("userId"), (String)row.get("username"));
            users.add(user);
        }
        return users;
    }

    public int addUserToGroup(int groupId, int userId) {
        String query = "INSERT INTO GroupParticipant (groupId, userId) VALUES (%d, %d)"
        String SQL = String.format(query, groupId, userId);
        jdbcTemplateObject.update(SQL);
        return groupId;
    }

    public User update(int userId, String username){
        String SQL = "UPDATE User SET username = '%s' WHERE userId = %d";
        jdbcTemplateObject.update(SQL, username, userId);
        return new User(userId, username);
    }

    public void delete(int groupId){
        String query = "DELETE FROM `Group` WHERE groupId = %d";
        String SQL = String.format(query,groupId);
        jdbcTemplateObject.update(SQL);
    }

    public Group update(int groupId, String groupname){
        String SQL = "UPDATE `Group` SET groupname = '%s' WHERE groupId = %d";
        jdbcTemplateObject.update(SQL, groupname, groupId);
        return new Group(groupId, groupname);
    }

}
