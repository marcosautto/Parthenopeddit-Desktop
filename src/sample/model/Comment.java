package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Comment {
    private int id;
    private String body;
    private String timestamp;
    private String author_id;
    private User author;
    private ObservableList<Comment> comments = FXCollections.observableArrayList();   //if crashes, initialize this in constructor
    private int comments_num;
    private int upvotes_num;
    private int downvotes_num;
    private int commented_content_id;

    public Comment(int id, String body, String timestamp, String author_id, User author, int upvotes_num, int downvotes_num, int commented_content_id){
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.author_id = author_id;
        this.author = author;
        this.upvotes_num = upvotes_num;
        this.downvotes_num = downvotes_num;
        this.commented_content_id = commented_content_id;
    }

    public int getId(){ return id; }
    public String getBody(){ return body; }
    public String getTimestamp(){ return timestamp; }
    public String getAuthor_id(){ return author_id; }
    public User getAuthor(){ return author; }
    public int getUpvotes(){ return upvotes_num; }
    public int getDownvotes(){ return downvotes_num; }
    private int getCommentedContentId(){ return commented_content_id; }
}
