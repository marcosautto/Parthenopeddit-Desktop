package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Group {

    private static int groupIdAct = 0;
    private int groupId;
    private String name;
    private int posts_num;
    private int members_num;
    private String created_on;
    private ObservableList<Post> posts = FXCollections.observableArrayList();

    public Group(String name, String created_on) {
        groupId = groupIdAct++;
        this.name = name;
        posts_num = 0;
        members_num = 0;
        this.created_on = created_on;
    }

    public int getId(){ return groupId; }
    public String getName(){ return name; }
    public String getCreatedOn(){ return created_on; }
    public int getPostsNum(){ return posts_num; }
    public ObservableList<Post> getPosts(){ return posts; }

}
