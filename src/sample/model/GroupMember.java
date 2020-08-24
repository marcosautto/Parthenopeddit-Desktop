package sample.model;

public class GroupMember {
    private String user_id;
    private String group_id;
    private String join_date;
    private boolean is_owner;
    private User user;
    private Group group;

    public GroupMember(String user_id, String group_id, String join_date, boolean is_owner, User user, Group group){
        this.user_id = user_id;
        this.group_id = group_id;
        this.join_date = join_date;
        this.is_owner = is_owner;
        this.user = user;
        this.group = group;
    }

    public String getUserId(){ return user_id; }
    public String getGroupId(){ return group_id; }
    public String getJoinDate(){ return join_date; }
    public Boolean getIsOwner(){ return is_owner; }
    public User getUser(){ return user; }
    public Group getGroup(){ return group; }

}
