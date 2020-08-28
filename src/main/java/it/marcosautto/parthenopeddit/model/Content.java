package it.marcosautto.parthenopeddit.model;

import javafx.collections.ObservableList;

public abstract class Content {

    //TODO: optimize extends

    private int id;
    private String body;
    private String timestamp;
    private String author_id;
    private String type;

    private User author;
    private ObservableList<Comment> comments;

    private int comments_num;
    private int upvotes_num;
    private int downvotes_num;

    Content(int id, String body, String timestamp, String author_id, String type, User author, int upvotes, int downvotes, ObservableList<Comment> comments){
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.author_id = author_id;
        this.type = type;
        this.author = author;
        this.comments = comments;
        this.comments_num = comments.size();
        this.upvotes_num = upvotes;
        this.downvotes_num = downvotes;
    }

    public int getId(){ return id; }
    public String getBody(){ return body; }
    public String getTimestamp(){ return timestamp; }
    public String getAuthorId(){ return author_id; }
    public User getAuthor(){ return author; }
    public String getType(){ return type; }
    public ObservableList<Comment> getComments(){ return comments; }
    public int getUpvote(){ return upvotes_num; }
    public int getDownvote(){ return downvotes_num; }
    public int getCommentsNum(){ return comments_num; }
    public void addComment(Comment comment){ comments.add(comment); comments_num++; }
    public void increaseUpvote(){ upvotes_num++; }
    public void increaseDownvote(){ downvotes_num++; }
}
