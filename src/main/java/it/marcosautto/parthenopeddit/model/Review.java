package it.marcosautto.parthenopeddit.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Review extends Content {

    private int reviewed_course_id;
    private int score_liking;
    private int score_difficulty;
    private Course reviewed_course;

    public Review(int id, String body, String timestamp, String author_id, User author, ArrayList<Comment> comments, int comments_num, int upvotes, int downvotes, int reviewed_course_id, int score_liking, int score_difficulty, Course reviewed_course){
        super(id, body, timestamp, author_id, "review", author, comments, comments_num, upvotes, downvotes);
        this.score_liking = score_liking;
        this.score_difficulty = score_difficulty;
        this.reviewed_course = reviewed_course;
        this.reviewed_course_id = reviewed_course_id;
    }

    public int getScoreLiking(){ return score_liking; }
    public int getScoreDifficulty(){ return score_difficulty; }
    public Course getReviewedCourse(){ return reviewed_course; }
    public int getReviewedCourseId(){ return reviewed_course_id; }


}
