package sample;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import sample.coursePage.CoursePostController;
import sample.coursePage.CourseReviewController;
import sample.model.Course;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CoursePageController implements Initializable {

    private static CoursePageController instance;
    public CoursePageController() { instance = this; }
    // static method to get instance of view
    public static CoursePageController getInstance() {
        return instance;
    }

    private DashboardController DashboardController;

    private Mockdatabase Mockdatabase;


    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;

        // Add observable list data to the table
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

    public boolean followed = false;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();
        try
        {
            AnchorPane anch1 = loader.load(getClass().getResource("fxml/CourseReviewLayout.fxml"));
            reviewTab.setContent(anch1);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab1");
        }

        loader = new FXMLLoader();
        try{
            AnchorPane anch2 = loader.load(getClass().getResource("fxml/CoursePostLayout.fxml"));
            postTab.setContent(anch2);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab2");
        }

        handleFollow();

    }

    public void transferMessage(int course_Id) {

        Course mCourse = Mockdatabase.getInstance().courses_table.stream().filter(course -> course.getCourseId() == course_Id).collect(Collectors.toList()).get(0);
        courseId = mCourse.getCourseId();
        courseNameTitleLabel.setText(mCourse.getName());
        average_liking_score_ratingbar.setRating(mCourse.getAverageLikingScore());
        average_difficulty_score_ratingbar.setRating(mCourse.getAverageDifficultyScore());
        average_liking_score_label.setText(mCourse.getAverageLikingScore() + " / 5");
        average_difficulty_score_label.setText(mCourse.getAverageDifficultyScore() + " / 5");
        reviews_count_label.setText(Integer.toString(mCourse.getReviewsCount()));

        CourseReviewController.getInstance().sendReviews(mCourse.getReviews());
        System.out.println(mCourse.getReviews().size());
        CoursePostController.getInstance().sendPosts(mCourse.getPosts());

    }

    public void writeReview() throws IOException {
        DashboardController.getInstance().writeReview(courseId);
    }

    public void handleFollow(){
        //API
        //        //se è true, allora il corso è seguito e premendo il pulsante il corso viene lasciato

        if(followed){
            writeReviewButton.setDisable(true);
            writePostButton.setDisable(true);
            followButton.setText("Segui corso");
            followed = false;
        } else {
            writeReviewButton.setDisable(false);
            writePostButton.setDisable(false);
            followButton.setText("Lascia corso");
            followed = true;
        }
    }
}
