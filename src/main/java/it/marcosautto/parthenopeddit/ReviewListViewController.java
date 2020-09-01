package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import it.marcosautto.parthenopeddit.model.Review;

import java.io.IOException;

public class ReviewListViewController extends ListCell<Review> {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label timestampLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label reviewNameLabel;

    @FXML
    private Rating average_liking_score_ratingbar;

    @FXML
    private Rating average_difficulty_score_ratingbar;

    @FXML
    private TextArea reviewBodyTextArea;

    @FXML
    private Label commentLabel;

    @FXML
    private Button commentButton;
    
    @FXML
    private Label upvoteLabel;

    @FXML
    private Button upvoteButton;
    
    @FXML
    private Label downvoteLabel;

    @FXML
    private Button downvoteButton;

    @FXML
    private AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Review review, boolean empty) {
        super.updateItem(review, empty);

        if(empty || review == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/ListCell/ReviewListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            usernameLabel.setText(review.getAuthorId());
            timestampLabel.setText(review.getTimestamp());
            courseNameLabel.setText(review.getReviewedCourse().getName());
            average_liking_score_ratingbar.setRating(review.getScoreLiking());
            average_difficulty_score_ratingbar.setRating(review.getScoreDifficulty());
            reviewBodyTextArea.setText(review.getBody());
            commentLabel.setText(Integer.toString(review.getCommentsNum()));
            upvoteLabel.setText(Integer.toString(review.getUpvote()));
            downvoteLabel.setText(Integer.toString(review.getDownvote()));
            courseNameLabel.setStyle("-fx-background-color: #006FFF; -fx-background-radius: 20; -fx-text-fill: #FFFFFF; -fx-label-padding: 5");


            courseNameLabel.setOnMouseClicked(e ->{
                try {
                    DashboardController.getInstance().courseSelected(review.getReviewedCourseId());
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

            });

            anchorPane.setOnMouseClicked(e ->{
                try {
                    System.out.println(review.getId());
                    DashboardController.getInstance().reviewSelected(review.getId(), review.getReviewedCourse().getName());
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            });

            commentButton.setOnMouseClicked(e ->{
                try {
                    System.out.println(review.getId());
                    DashboardController.getInstance().reviewSelected(review.getId(), review.getReviewedCourse().getName());
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            });

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
