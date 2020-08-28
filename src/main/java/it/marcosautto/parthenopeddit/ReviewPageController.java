package it.marcosautto.parthenopeddit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import it.marcosautto.parthenopeddit.model.Comment;
import it.marcosautto.parthenopeddit.model.Review;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReviewPageController implements Initializable {

        private static it.marcosautto.parthenopeddit.ReviewPageController instance;
        public ReviewPageController() { instance = this; }
        // static method to get instance of view
        public static it.marcosautto.parthenopeddit.ReviewPageController getInstance() {
            return instance;
        }

        private DashboardController DashboardController;

        private it.marcosautto.parthenopeddit.api.Mockdatabase Mockdatabase;


        public void setDashboardController(DashboardController dashboardController) {
            this.DashboardController = dashboardController;

            // Add observable list data to the table
        }

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
        private AnchorPane reviewAnchorPane;

        @FXML
        private TextArea commentTextArea;

        @FXML
        private Button sendCommentButton;

        @FXML
        private ListView<Comment> commentListView;

        private ObservableList<Comment> commentObservableList;

        private Review review;

        @Override
        public void initialize(URL location, ResourceBundle resources)
        {

        }

        public void transferMessage(int review_id) {
            System.out.println(review_id);

            //-----POST-----
            review = Mockdatabase.getInstance().review_table.stream().filter(review -> review.getId() == review_id).collect(Collectors.toList()).get(0);

            usernameLabel.setText(review.getAuthorId());
            courseNameLabel.setText(review.getReviewedCourse().getName());
            timestampLabel.setText(review.getTimestamp());
            courseNameLabel.setStyle("-fx-background-color: #006FFF; -fx-background-radius: 20; -fx-text-fill: #FFFFFF; -fx-label-padding: 5");
            reviewBodyTextArea.setText(review.getBody());
            upvoteLabel.setText(Integer.toString(review.getUpvote()));
            downvoteLabel.setText(Integer.toString(review.getDownvote()));
            commentLabel.setText(Integer.toString(review.getCommentsNum()));

            courseNameLabel.setOnMouseClicked(e ->{
                try {
                        DashboardController.getInstance().courseSelected(review.getReviewedCourseId());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
            });

            //-----COMMENT LIST-----

            commentObservableList = FXCollections.observableArrayList();

            if(review.getCommentsNum() > 0){
                commentObservableList.addAll(
                        review.getComments()
                );
                System.out.println(review.getComments().size());
                commentListView.setItems(commentObservableList);
                commentListView.setCellFactory(reviewListView -> new CommentListViewController());
            } else
                commentListView.setPlaceholder(new Label("Non ci sono commenti per questa recensione."));

        }

        public void sendComment(){
            if(!commentTextArea.getText().isEmpty()){
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());

                Comment comment = new Comment(1, commentTextArea.getText(), date, Mockdatabase.getInstance().user1.getId(), Mockdatabase.getInstance().user1, 0, 0, review.getId());

                Mockdatabase.getInstance().review_table.stream().filter(review -> review.getId() == this.review.getId()).collect(Collectors.toList()).get(0).addComment(comment);

                commentTextArea.clear();
            }
        }




}
