package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Comment;
import it.marcosautto.parthenopeddit.model.Post;

import java.io.IOException;

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

        private DashboardController DashboardController;

        @Override
        protected void updateItem(Comment comment, boolean empty) {
            super.updateItem(comment, empty);
            this.comment = comment;

            if(empty || comment == null) {

                setText(null);
                setGraphic(null);

            } else {
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("fxml/ListCell/CommentListCell.fxml"));
                    mLLoader.setController(this);

                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                usernameLabel.setText(comment.getAuthor_id());
                timestampLabel.setText(comment.getTimestamp());
                commentBodyTextArea.setText(comment.getBody());
                upvoteLabel.setText(Integer.toString(comment.getUpvotes()));
                downvoteLabel.setText(Integer.toString(comment.getDownvotes()));
                //commentLabel.setText(Integer.toString(comment.getCommentsNum()));


                setText(null);
                setGraphic(anchorPane);
            }

        }

}
