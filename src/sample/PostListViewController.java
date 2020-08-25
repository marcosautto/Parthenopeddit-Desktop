package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import sample.model.Post;

import java.io.IOException;

public class PostListViewController extends ListCell<Post> {

        @FXML
        private Label titleLabel;

        @FXML
        private Label authorLabel;

        @FXML
        private Label boardLabel;

        @FXML
        private TextArea postBodyTextArea;       //Uso la TextArea e non le Label per gestire meglio i testi multilinea

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

        @Override
        protected void updateItem(Post post, boolean empty) {
            super.updateItem(post, empty);

            if(empty || post == null) {

                setText(null);
                setGraphic(null);

            } else {
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("fxml/ListCell/PostListCell.fxml"));
                    mLLoader.setController(this);

                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                titleLabel.setText(post.getTitle());
                authorLabel.setText(post.getAuthorId());
                boardLabel.setText(post.getPostedToBoard().getName());
                if(post.getPostedToBoardId() == 1)
                    boardLabel.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-width: 2; -fx-border-radius: 20; -fx-label-padding: 5");
                else if(post.getPostedToBoard().getType() == "course")
                    boardLabel.setStyle("-fx-background-color: #006FFF; -fx-background-radius: 20; -fx-text-fill: #FFFFFF; -fx-label-padding: 5");
                else
                    boardLabel.setStyle("-fx-background-color: #FF545D; -fx-background-radius: 20; -fx-text-fill: #FFFFFF; -fx-label-padding: 5");

                postBodyTextArea.setText(post.getBody());
                upvoteLabel.setText(Integer.toString(post.getUpvote()));
                downvoteLabel.setText(Integer.toString(post.getDownvote()));
                commentLabel.setText(Integer.toString(post.getCommentsNum()));
                //contentLabel.setText(post.get);


                setText(null);
                setGraphic(anchorPane);
            }

        }
    }




