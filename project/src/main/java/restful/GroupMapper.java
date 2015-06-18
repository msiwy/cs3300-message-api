package main.java.restful;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gatsby on 6/16/15.
 */
public class GroupMapper implements RowMapper<Group> {
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group(rs.getInt("groupId"),rs.getString("groupname"));
        return group;
    }
}