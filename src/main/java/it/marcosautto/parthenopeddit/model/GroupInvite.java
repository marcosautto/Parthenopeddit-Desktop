package it.marcosautto.parthenopeddit.model;

public class GroupInvite {
    private String inviter_id;
    private String invited_id;
    private int group_id;
    private String timestamp;

    private User inviter;
    private User invited;
    private Group group;

    public GroupInvite(String inviter_id, String invited_id, int group_id, String timestamp, User inviter, User invited, Group group){
        this.inviter_id = inviter_id;
        this.invited_id = invited_id;
        this.group_id = group_id;
        this.timestamp = timestamp;
        this.inviter = inviter;
        this.invited = invited;
        this.group = group;
    }

    public String getInviterId(){ return inviter_id; }
    public String getInvitedId(){ return invited_id; }
    public int getGroupId(){ return group_id; }
    public String getTimestamp(){ return timestamp; }
    public User getInviter(){ return inviter; }
    public User getInvited(){ return invited; }
    public Group getGroup(){ return group; }
}
