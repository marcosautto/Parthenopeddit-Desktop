package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Group extends Board {

    private ObservableList<GroupMember> members = FXCollections.observableArrayList();
    private ObservableList<GroupInvite> invites = FXCollections.observableArrayList();
    private int members_num;
    private String created_on;

    public Group(int id, String name, String created_on) {
        super(id, name, "group");
        members_num = members.size();
        this.created_on = created_on;
    }

    public int getId(){ return super.getBoardId(); }
    public String getName(){ return super.getName(); }
    public String getCreatedOn(){ return created_on; }
    public int getPostsNum(){ return super.getPostsNum(); }
    public ObservableList<Post> getPosts(){ return super.getPosts(); }
    public ObservableList<GroupMember> getMembers(){ return members; }
    public ObservableList<GroupInvite> getInvites(){ return invites; }

}
