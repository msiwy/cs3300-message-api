package main.java.restful;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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