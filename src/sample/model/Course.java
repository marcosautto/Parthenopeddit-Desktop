package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Course {
    
    //private static int courseIdAct = 0;
    private int courseId;
    private String name;
    private int posts_num;

    private double average_liking_score;
    private Integer follower_number;
    private double average_difficulty_score;
    private Integer reviews_count;
    private ObservableList<Post> posts = FXCollections.observableArrayList();
    private ObservableList<Review> reviews = FXCollections.observableArrayList();
    private ObservableList<User> followers = FXCollections.observableArrayList();

    public Course(String name, double average_liking_score, double average_difficulty_score, int id) {
        //courseId = courseIdAct++;
        courseId = id;
        this.name = name;
        posts_num = 0;
        this.average_liking_score = average_liking_score;
        follower_number = 0;
        this.average_difficulty_score = average_difficulty_score;
        reviews_count = 0;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostsNum() { return posts_num; }
    public Integer getFollowerNumber() { return follower_number; }
    public double getAverageLikingScore() { return average_liking_score; }
    public Integer getReviewsCount() { return reviews_count; }
    public double getAverageDifficultyScore() { return average_difficulty_score; }
    public ObservableList<Post> getPosts(){ return posts; }
    public ObservableList<Review> getReviews(){ return reviews; }




}
