package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.CommentsRequests;
import it.marcosautto.parthenopeddit.util.DateParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Comment;

import java.io.IOException;
import java.text.ParseException;

public class CommentListViewController extends ListCell<Comment> {

        @FXML
        private Label usernameLabel;

        @FXML
        private Label timestampLabel;

        @FXML
        private TextArea commentBodyTextArea;       //Uso la TextArea e non le Label per gestire meglio i testi multilinea

        @FXML
        private Label commentLabel;

        @FXML
        private Label upvoteLabel;

        @FXML
        private Label downvoteLabel;

        @FXML
        private Button commentButton;

        @FXML
        private Button upvoteButton;

        @FXML
        private Button downvoteButton;

        @FXML
        private AnchorPane anchorPane;

        private FXMLLoader mLLoader;

        private Comment comment;

        private CommentsRequests CommentsRequests = new CommentsRequests();

        private DashboardController DashboardController;

        private DateParser DateParser;

    @Override
        protected void updateItem(Comment comment, boolean empty) {
            super.updateItem(comment, empty);
            DateParser = new DateParser();
            this.comment = comment;

            if(empty || comment == null) {

                setText(null);
                setGraphic(null);

            } else {
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("/ListCell/CommentListCell.fxml"));
                    mLLoader.setController(this);

                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                usernameLabel.setText(comment.getAuthorId());
                try {
                    timestampLabel.setText(DateParser.parseDate(comment.getTimestamp()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                commentBodyTextArea.setText(comment.getBody());
                upvoteLabel.setText(Integer.toString(comment.getUpvote()));
                downvoteLabel.setText(Integer.toString(comment.getDownvote()));
                //commentLabel.setText(Integer.toString(comment.getCommentsNum()));


                upvoteButton.setOnMouseClicked(e ->{
                    try {
                        CommentsRequests.likeComment(comment.getId());
                        updateVotes();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                });

                downvoteButton.setOnMouseClicked(e ->{
                    try {
                        CommentsRequests.dislikeComment(comment.getId());
                        updateVotes();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });




                setText(null);
                setGraphic(anchorPane);
            }

        }

    private void updateVotes() throws IOException, InterruptedException {
        comment = CommentsRequests.getComment(comment.getId());
        upvoteLabel.setText(Integer.toString(comment.getUpvote()));
        downvoteLabel.setText(Integer.toString(comment.getDownvote()));

    }

}
