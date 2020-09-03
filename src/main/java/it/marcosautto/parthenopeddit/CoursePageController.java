package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.CoursesRequests;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.model.Review;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import it.marcosautto.parthenopeddit.coursePage.CoursePostController;
import it.marcosautto.parthenopeddit.coursePage.CourseReviewController;
import it.marcosautto.parthenopeddit.model.Course;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CoursePageController implements Initializable {

    private static CoursePageController instance;

    public CoursePageController() { instance = this; }

    public static CoursePageController getInstance() {
        return instance;
    }

    private DashboardController DashboardController;

    private CoursesRequests CoursesRequests;

    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;
    }

    @FXML
    public TabPane tabPane;

    @FXML
    public Tab reviewTab;

    @FXML
    public CourseReviewController courseReviewController;

    @FXML
    public Tab postTab;

    @FXML
    public CoursePostController coursePostController;

    @FXML
    public Label courseNameTitleLabel;

    @FXML
    public Rating average_liking_score_ratingbar;

    @FXML
    public Label average_liking_score_label;

    @FXML
    public Rating average_difficulty_score_ratingbar;

    @FXML
    public Label average_difficulty_score_label;

    @FXML
    public Label reviews_count_label;

    @FXML
    public Button followButton;

    @FXML
    public Button writeReviewButton;

    @FXML
    public Button writePostButton;

    @FXML
    public Button test;

    public int courseId;

    public Course course;

    public boolean isFollowed = false;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();

        CoursesRequests = new CoursesRequests();

        try
        {
            AnchorPane anch1 = loader.load(getClass().getResource("/CourseReviewLayout.fxml"));
            reviewTab.setContent(anch1);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab1");
        }

        loader = new FXMLLoader();
        try{
            AnchorPane anch2 = loader.load(getClass().getResource("/CoursePostLayout.fxml"));
            postTab.setContent(anch2);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab2");
        }

    }

    public void transferMessage(int course_Id) throws IOException, InterruptedException {

        course = CoursesRequests.getCourseByID(course_Id);
        courseId = course.getCourseId();
        courseNameTitleLabel.setText(course.getName());
        average_liking_score_ratingbar.setRating(course.getAverageLikingScore());
        average_difficulty_score_ratingbar.setRating(course.getAverageDifficultyScore());
        average_liking_score_label.setText(course.getAverageLikingScore() + " / 5");
        average_difficulty_score_label.setText(course.getAverageDifficultyScore() + " / 5");
        reviews_count_label.setText(Integer.toString(course.getReviewsCount()));

        ObservableList<Review> reviews = CoursesRequests.getCourseReviews(course.getCourseId(), 1, 10, null);
        ObservableList<Post> posts = CoursesRequests.getCoursePosts(course.getCourseId(), 1, 10, null);

        ObservableList<Course> followedCourses = CoursesRequests.getFollowedCourses();
        isFollowed = followedCourses.stream().anyMatch(course -> course.getCourseId() == course.getCourseId());

        if(isFollowed){
            writeReviewButton.setDisable(false);
            writePostButton.setDisable(false);
            followButton.setText("Lascia corso");
        } else {
            writeReviewButton.setDisable(true);
            writePostButton.setDisable(true);
            followButton.setText("Segui corso");
        }

        CourseReviewController.getInstance().sendReviews(reviews);
        CoursePostController.getInstance().sendPosts(posts);

    }

    public void writePost() throws IOException {
        DashboardController.getInstance().publishPost(courseId, course.getName());
    }

    public void writeReview() throws IOException, InterruptedException {
        DashboardController.getInstance().writeReview(courseId);
    }

    public void handleFollow() throws IOException, InterruptedException {

        if(isFollowed){
            writeReviewButton.setDisable(true);
            writePostButton.setDisable(true);
            followButton.setText("Segui corso");
            isFollowed = false;
            CoursesRequests.unfollowCourse(courseId);
        } else {
            writeReviewButton.setDisable(false);
            writePostButton.setDisable(false);
            followButton.setText("Lascia corso");
            isFollowed = true;
            CoursesRequests.followCourse(courseId);
        }

    }
}
