package main.java.restful;

/**
 * Created by Gatsby on 6/10/15.
 */
public class Group {
    public String groupname;
    public int groupId;
    public Group(String groupname) {
        this.groupname = groupname;
        this.groupId = groupname.hashCode();
    }
}
