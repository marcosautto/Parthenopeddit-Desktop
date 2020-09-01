package it.marcosautto.parthenopeddit.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Group extends Board {

    private String created_on;
    private ArrayList<GroupMember> members;
    private ArrayList<GroupChat> chat;
    private ArrayList<GroupInvite> invites;
    private int members_num;

    public Group(int id,
                 String name,
                 ArrayList<Post> posts,
                 int posts_num,
                 String created_on,
                 ArrayList<GroupMember> members,
                 ArrayList<GroupChat> chat,
                 ArrayList<GroupInvite> invites,
                 int members_num) {
        super(id, name, "group", posts, posts_num);
        this.members = members;
        this.chat = chat;
        this.invites = invites;
        this.members_num = members_num;
        this.created_on = created_on;
    }

    public int getId(){ return super.getBoardId(); }
    public String getName(){ return super.getName(); }
    public String getCreatedOn(){ return created_on; }
    public int getPostsNum(){ return super.getPostsNum(); }
    public ArrayList<Post> getPosts(){ return super.getPosts(); }
    public ArrayList<GroupMember> getMembers(){ return members; }
    public ArrayList<GroupInvite> getInvites(){ return invites; }

}
