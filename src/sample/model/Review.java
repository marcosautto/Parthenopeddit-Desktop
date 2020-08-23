package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Review /*extends Content*/ {

    //TODO: optimize extends

    private static int reviewIdAct = 0;
    private int reviewId;
    private String body;
    private String timestamp;
    private String author_id;
    private User author;
    private ObservableList<Comment> comments = FXCollections.observableArrayList();
    private int comments_num;
    private int upvotes;
    private int downvotes;

    private int reviewed_course_id;
    private int score_liking;
    private int score_difficulty;
    private Course reviewed_course;

    public Review(String body, String timestamp, String author_id, User author, ObservableList<Comment> comments, int upvotes, int downvotes, int reviewed_course_id, int score_liking, int score_difficulty, Course reviewed_course){
        reviewId = reviewIdAct++;
        this.body = body;
        this.timestamp = timestamp;
        this.author = author;
        this.author_id = author_id;
        this.comments = comments;

        //if(comments == null){ this.comments_num = 0;} else{ this.comments_num= comments.size(); }
        this.comments_num = comments.size();

        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.score_liking = score_liking;
        this.score_difficulty = score_difficulty;
        this.reviewed_course = reviewed_course;
        this.reviewed_course_id = reviewed_course_id;
    }

    public String getBody(){ return body; }
    public String getTimestamp(){ return timestamp; }
    public int getScoreLiking(){ return score_liking; }
    public int getScoreDifficulty(){ return score_difficulty; }
    public Course getReviewedCourse(){ return reviewed_course; }
    public String getAuthorId() { return author_id; }
    public ObservableList<Comment> getComments(){ return comments; }
    public int getUpvote(){ return upvotes; }
    public int getDownvote(){ return downvotes; }


}
