package it.marcosautto.parthenopeddit.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public abstract class Content {

    //TODO: optimize extends

    private int id;
    private String body;
    private String timestamp;
    private String author_id;
    private String type;

    private User author;
    private ArrayList<Comment> comments;

    private int comments_num;
    private int upvotes_num;
    private int downvotes_num;

    Content(int id, String body, String timestamp, String author_id, String type, User author, ArrayList<Comment> comments, int comments_num, int upvotes, int downvotes){
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.author_id = author_id;
        this.type = type;
        this.author = author;
        this.comments = comments;
        this.comments_num = comments_num;
        this.upvotes_num = upvotes;
        this.downvotes_num = downvotes;
    }

    public int getId(){ return id; }
    public String getBody(){ return body; }
    public String getTimestamp(){ return timestamp; }
    public String getAuthorId(){ return author_id; }
    public User getAuthor(){ return author; }
    public String getType(){ return type; }
    public ArrayList<Comment> getComments(){ return comments; }
    public void setComments(ArrayList<Comment> comments){ this.comments = comments; }
    public int getUpvote(){ return upvotes_num; }
    public int getDownvote(){ return downvotes_num; }
    public int getCommentsNum(){ return comments_num; }
    public void addComment(Comment comment){ comments.add(comment); comments_num++; }
    public void increaseUpvote(){ upvotes_num++; }
    public void increaseDownvote(){ downvotes_num++; }

}
