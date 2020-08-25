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
        private Label contentLabel;

        @FXML
        private TextArea postContentTextArea;       //Uso la TextArea e non le Label per gestire meglio i testi multilinea

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
                postContentTextArea.setText(post.getPostContent());
                upvoteLabel.setText(Integer.toString(post.getUpvote()));
                downvoteLabel.setText(Integer.toString(post.getDownvote()));
                //contentLabel.setText(post.get);

                if(post.getContent().equals(Post.CONTENT.HOME)) {
                    contentLabel.setText("Home");
                } else if(post.getContent().equals(Post.CONTENT.GROUP)) {
                    contentLabel.setText("Group");
                } else {
                    contentLabel.setText("Course");
                }

                setText(null);
                setGraphic(anchorPane);
            }

        }
    }




