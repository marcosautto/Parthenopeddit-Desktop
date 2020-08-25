package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Course extends Board {
    
    //private static int courseIdAct = 0;

    private double average_liking_score;
    private Integer follower_number;
    private double average_difficulty_score;
    private Integer reviews_count;
    private ObservableList<Review> reviews = FXCollections.observableArrayList();
    private ObservableList<User> followers = FXCollections.observableArrayList();

    public Course(String name, double average_liking_score, double average_difficulty_score, int id) {
        super(id, name, "course");
        this.average_liking_score = average_liking_score;
        follower_number = 0;
        this.average_difficulty_score = average_difficulty_score;
        reviews_count = reviews.size();
    }

    public int getCourseId() {
        return super.getBoardId();
    }

    public String getName() { return super.getName(); }

    public int getPostsNum() { return super.getPostsNum(); }
    public Integer getFollowerNumber() { return follower_number; }
    public double getAverageLikingScore() { return average_liking_score; }
    public void setReviewsCount(int reviews_count){ this.reviews_count = reviews_count; }
    public Integer getReviewsCount() { return reviews_count; }
    public double getAverageDifficultyScore() { return average_difficulty_score; }
    public ObservableList<Post> getPosts(){ return super.getPosts(); }
    public ObservableList<Review> getReviews(){ return reviews; }




}
