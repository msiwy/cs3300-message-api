package main.java.hello;

/**
 * Created by gatsby on 6/14/15.
 */
public class GroupParticipant {
    public final long gpId;
    public int userId;
    public int groupId;

    public GroupParticipant(long gpId, int groupId ,int userId) {
        this.gpId = gpId;
        this.userId = userId;
        this.groupId = groupId;
    }
}