package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.CoursesRequests;
import it.marcosautto.parthenopeddit.api.ReviewsRequests;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.controlsfx.control.Rating;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.Course;
import it.marcosautto.parthenopeddit.model.Review;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class NewReviewController {

    @FXML
    public Label courseNameLabel;

    @FXML
    public Rating average_enjoyment_score_ratingbar;

    @FXML
    public Rating average_difficulty_score_ratingbar;

    @FXML
    public TextArea reviewTextArea;

    @FXML
    public Button cancelButton;

    @FXML
    public Button publishButton;

    public CoursesRequests CoursesRequests;

    public ReviewsRequests ReviewsRequests;

    private static NewReviewController instance;
    public NewReviewController() {
        instance = this;
    }

    public static NewReviewController getInstance() {
        return instance;
    }

    public Course course;

    public void transferMessage(int courseId) throws IOException, InterruptedException {

        CoursesRequests = new CoursesRequests();
        ReviewsRequests = new ReviewsRequests();


        course = CoursesRequests.getCourseByID(courseId);
        courseNameLabel.setText(course.getName());
    }

    public void handlePublish() throws IOException, InterruptedException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        //Review review = new Review(99, reviewTextArea.getText(), formatter.format(date), Mockdatabase.getInstance().user1.getId(), Mockdatabase.getInstance().user1,  Mockdatabase.getInstance().noComments, 0, 0, course.getCourseId(), (int) average_enjoyment_score_ratingbar.getRating(), (int) average_difficulty_score_ratingbar.getRating(), course);
        //Mockdatabase.getInstance().courses_table.stream().filter(course -> course.getCourseId() == course.getCourseId()).collect(Collectors.toList()).get(0).getReviews().add(review);
        ReviewsRequests.publishNewReview(reviewTextArea.getText(), course.getCourseId(), (int) average_enjoyment_score_ratingbar.getRating(), (int) average_difficulty_score_ratingbar.getRating());
        DashboardController.getInstance().courseSelected(course.getCourseId());
    }

    public void handleCancel() throws IOException, InterruptedException {
        DashboardController.getInstance().courseSelected(course.getCourseId());
    }


}
