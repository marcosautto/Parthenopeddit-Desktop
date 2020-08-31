package it.marcosautto.parthenopeddit.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Course extends Board {
    
    //private static int courseIdAct = 0;

    private double average_liking_score;
    private Integer follower_number;
    private double average_difficulty_score;
    private Integer reviews_count;
    private ArrayList<Review> reviews;
    private ArrayList<User> followers;

    public Course(
            int id,
            String name,
            ArrayList<Post> posts,
            int posts_num,
            double average_liking_score,
            int follower_number,
            double average_difficulty_score,
            int reviews_count,
            ArrayList<Review> reviews,
            ArrayList<User> followes) {
        super(id, name, "course", posts, posts_num);
        this.average_liking_score = average_liking_score;
        follower_number = 0;
        this.average_difficulty_score = average_difficulty_score;
        reviews_count = reviews.size();
    }

    public int getCourseId() { return super.getBoardId(); }

    public String getName() { return super.getName(); }

    public int getPostsNum() { return super.getPostsNum(); }
    public Integer getFollowerNumber() { return follower_number; }
    public double getAverageLikingScore() { return average_liking_score; }
    public void setReviewsCount(int reviews_count){ this.reviews_count = reviews_count; }
    public Integer getReviewsCount() { return reviews_count; }
    public double getAverageDifficultyScore() { return average_difficulty_score; }
    public ArrayList<Post> getPosts(){ return super.getPosts(); }
    public ArrayList<Review> getReviews(){ return reviews; }




}
