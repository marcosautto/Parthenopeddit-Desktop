package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import it.marcosautto.parthenopeddit.model.Course;

import java.io.IOException;

public class CourseListViewController extends ListCell<Course> {

    @FXML
    private Label courseNameLabel;

    @FXML
    private Rating average_liking_score_ratingbar;

    @FXML
    private Rating average_difficulty_score_ratingbar;

    @FXML
    private AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Course course, boolean empty) {
        super.updateItem(course, empty);

        if(empty || course == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("fxml/ListCell/CourseListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            courseNameLabel.setText(course.getName());
            average_liking_score_ratingbar.setRating(course.getAverageLikingScore());
            average_difficulty_score_ratingbar.setRating(course.getAverageDifficultyScore());

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
