package sample.profilePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.DashboardController;
import sample.ReviewListViewController;
import sample.api.Mockdatabase;
import sample.coursePage.CourseReviewController;
import sample.model.Review;

import java.net.URL;
import java.util.ResourceBundle;

public class UserReviewController implements Initializable {

    private static UserReviewController instance;

    public static UserReviewController getInstance() {
        return instance;
    }

    @FXML
    private ListView<Review> reviewListView;

    private ObservableList<Review> reviewObservableList;

    private Mockdatabase Mockdatabase;


    public UserReviewController()  {

        instance = this;
        reviewObservableList = FXCollections.observableArrayList();
        
        reviewObservableList.addAll( Mockdatabase.getInstance().user1.getPublishedReviews() );
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reviewListView.setItems(reviewObservableList);
        reviewListView.setCellFactory(reviewListView -> new ReviewListViewController());

    }

}
