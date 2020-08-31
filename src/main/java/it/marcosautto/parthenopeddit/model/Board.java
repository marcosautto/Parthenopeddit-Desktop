package it.marcosautto.parthenopeddit.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Board {

    private int id;
    private String name;
    private String type;

    private ArrayList<Post> posts;
    private int posts_num;


    public Board(int id, String name, String type, ArrayList<Post> posts, int posts_num){
        this.id = id;
        this.name = name;
        this.type = type;
        this.posts = posts;
        this.posts_num = posts_num;
    }

    public int getBoardId() { return id; }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPostsNum() { return posts_num; }

    public ArrayList<Post> getPosts(){ return posts; }
}
