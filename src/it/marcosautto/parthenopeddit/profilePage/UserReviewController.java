package it.marcosautto.parthenopeddit.profilePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.DashboardController;
import it.marcosautto.parthenopeddit.PostListViewController;
import it.marcosautto.parthenopeddit.ReviewListViewController;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.coursePage.CourseReviewController;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.model.Review;

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
        //reviewListView.setItems(reviewObservableList);
        //reviewListView.setCellFactory(reviewListView -> new ReviewListViewController());

    }

    public void sendReviews(ObservableList<Review> reviews){
        if(reviews.size() > 0){
            reviewListView.setItems(reviews);
            reviewListView.setCellFactory(postListView -> new ReviewListViewController());
        } else
            reviewListView.setPlaceholder(new Label("Non hai ancora pubblicato alcuna recensione."));
    }

}
