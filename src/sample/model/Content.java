package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Content {

    //TODO: optimize extends

    private int id;
    private String body;
    private String timestamp;
    private String author_id;
    private String type;

    private User author;
    private ObservableList<Comment> comments = FXCollections.observableArrayList();

    private int comments_num;
    private int upvotes_num;
    private int downvotes_num;

    //Content(int id, )

    public int getId(){ return id; }
    public String getBody(){ return body; }
    public String getTimestamp(){ return timestamp; }
    public String getAuthor_id(){ return author_id; }
    public User getAuthor(){ return author; }
    public ObservableList<Comment> getComments(){ return comments; }
    public int getUpvotes(){ return upvotes_num; }
    public int getDownvotes(){ return downvotes_num; }
    public int getCommentsNum(){ return comments_num; }
}
