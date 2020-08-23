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

    }

    public void transferMessage(int courseId) {
        //Display the message
        Mockdatabase mockdatabase = new Mockdatabase();

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).

        Course mCourse = mockdatabase.courses_table.stream().filter(course -> course.getCourseId() == courseId).collect(Collectors.toList()).get(0);
        courseNameTitleLabel.setText(mCourse.getName());
        average_liking_score_ratingbar.setRating(mCourse.getAverageLikingScore());
        average_difficulty_score_ratingbar.setRating(mCourse.getAverageDifficultyScore());
        average_liking_score_label.setText(mCourse.getAverageLikingScore() + " / 5");
        average_difficulty_score_label.setText(mCourse.getAverageDifficultyScore() + " / 5");
        reviews_count_label.setText(Integer.toString(mCourse.getReviewsCount()));

        courseReviewController.getInstance().sendReviews(mCourse.getReviews());
        coursePostController.getInstance().sendPosts(mCourse.getPosts());


    }
}
