package it.marcosautto.parthenopeddit.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Board {

    private int id;
    private String name;
    private String type;

    private ObservableList<Post> posts = FXCollections.observableArrayList();
    private int posts_num;

    public Board(int id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
        this.posts = posts;
        posts_num = posts.size();
    }

    public int getBoardId() { return id; }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPostsNum() { return posts_num; }

    public ObservableList<Post> getPosts(){ return posts; }
}
