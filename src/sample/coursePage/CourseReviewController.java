package sample.coursePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.ReviewListViewController;
import sample.model.Review;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseReviewController implements Initializable {

    @FXML
    private ListView<Review> reviewListView;

    private ObservableList<Review> reviewObservableList;

    private static CourseReviewController instance;
    // static method to get instance of view
    public static CourseReviewController getInstance() {
        return instance;
    }

    public CourseReviewController()  {

        instance = this;
        reviewObservableList = FXCollections.observableArrayList();

        //add some Students
        reviewObservableList.addAll(
        );


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //reviewListView.setItems(reviewObservableList);
        //reviewListView.setCellFactory(reviewListView -> new ReviewListViewController());

    }

    public void sendReviews(ObservableList<Review> reviews){
        reviewListView.setItems(reviews);
        reviewListView.setCellFactory(reviewListView -> new ReviewListViewController());
    }
}
