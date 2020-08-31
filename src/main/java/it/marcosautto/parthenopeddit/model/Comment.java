package it.marcosautto.parthenopeddit.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Comment {
    private int id;
    private String body;
    private String timestamp;
    private String author_id;
    private User author;
    private ArrayList<Comment> comments = new ArrayList<Comment>();   //if crashes, initialize this in constructor
    private int comments_num;
    private int likes_num;
    private int dislikes_num;
    private int commented_content_id;

    public Comment(int id, String body, String timestamp, String author_id, User author, int upvotes_num, int downvotes_num, int commented_content_id){
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.author_id = author_id;
        this.author = author;
        this.likes_num = upvotes_num;
        this.dislikes_num = downvotes_num;
        this.commented_content_id = commented_content_id;
    }

    public int getId(){ return id; }
    public String getBody(){ return body; }
    public String getTimestamp(){ return timestamp; }
    public String getAuthor_id(){ return author_id; }
    public User getAuthor(){ return author; }
    public int getUpvotes(){ return likes_num; }
    public int getDownvotes(){ return dislikes_num; }
    private int getCommentedContentId(){ return commented_content_id; }
}
