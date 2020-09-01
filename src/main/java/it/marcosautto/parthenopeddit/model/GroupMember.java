package it.marcosautto.parthenopeddit.model;

public class GroupMember {
    private String user_id;
    private int group_id;
    private String join_date;
    private String last_chat_read;
    private boolean is_owner;
    private User user;
    private Group group;

    public GroupMember(String user_id, int group_id, String join_date, String last_chat_read, boolean is_owner, User user, Group group){
        this.user_id = user_id;
        this.group_id = group_id;
        this.join_date = join_date;
        this.last_chat_read = last_chat_read;
        this.is_owner = is_owner;
        this.user = user;
        this.group = group;
    }

    public String getUserId(){ return user_id; }
    public int getGroupId(){ return group_id; }
    public String getJoinDate(){ return join_date; }
    public Boolean getIsOwner(){ return is_owner; }
    public User getUser(){ return user; }
    public Group getGroup(){ return group; }

}
