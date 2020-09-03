package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.PostsRequests;
import it.marcosautto.parthenopeddit.util.DateParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.api.Auth;

import java.io.IOException;
import java.text.ParseException;

public class PostListViewController extends ListCell<Post> {

    private Auth Auth;

    private PostsRequests PostRequests = new PostsRequests();

        @FXML
        private Label titleLabel;

        @FXML
        private Label authorLabel;

        @FXML
        private Label boardLabel;

        @FXML
        private Label timestampLabel;

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

        private DateParser DateParser;


    private Post post;

        private DashboardController DashboardController;

        @Override
        protected void updateItem(Post post, boolean empty) {
            super.updateItem(post, empty);
            DateParser = new DateParser();
            this.post = post;

            if(empty || post == null) {

                setText(null);
                setGraphic(null);

            } else {
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("/ListCell/PostListCell.fxml"));
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
                try {
                    timestampLabel.setText(DateParser.parseDate(post.getTimestamp()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(post.getPostedToBoardId() == 0){
                    boardLabel.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20; -fx-border-color: #000000; -fx-border-width: 2; -fx-border-radius: 20; -fx-label-padding: 5");
                    boardLabel.setText("Generale");
                } else if(post.getPostedToBoard().getType().equals("course"))
                    boardLabel.setStyle("-fx-background-color: #006FFF; -fx-background-radius: 20; -fx-text-fill: #FFFFFF; -fx-label-padding: 5");
                else
                    boardLabel.setStyle("-fx-background-color: #FF545D; -fx-background-radius: 20; -fx-text-fill: #FFFFFF; -fx-label-padding: 5");

                postBodyTextArea.setText(post.getBody());
                upvoteLabel.setText(Integer.toString(post.getUpvote()));
                downvoteLabel.setText(Integer.toString(post.getDownvote()));
                commentLabel.setText(Integer.toString(post.getCommentsNum()));

                boardLabel.setOnMouseClicked(e ->{
                    if(post.getPostedToBoardId() == 0) {
                        try {
                            DashboardController.getInstance().homeFXML(null);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else if(post.getPostedToBoard().getType().equals("course")) {
                        try {
                            DashboardController.getInstance().courseSelected(post.getPostedToBoardId());
                        } catch (IOException | InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        try {
                            DashboardController.getInstance().groupSelected(post.getPostedToBoardId());
                        } catch (IOException | InterruptedException | ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                anchorPane.setOnMouseClicked(e ->{
                    try {
                        DashboardController.getInstance().postSelected(post.getId());
                    } catch (IOException | InterruptedException | ParseException ex) {
                        ex.printStackTrace();
                    }
                });

                commentButton.setOnMouseClicked(e ->{
                    try {
                        DashboardController.getInstance().postSelected(post.getId());
                    } catch (IOException | InterruptedException | ParseException ex) {
                        ex.printStackTrace();
                    }
                });

                upvoteButton.setOnMouseClicked(e ->{
                    try {
                        PostRequests.likePost(post.getId());
                        updateVotes();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                });

                downvoteButton.setOnMouseClicked(e ->{
                    try {
                        PostRequests.dislikePost(post.getId());
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
            post = PostRequests.getPost(post.getId());
            upvoteLabel.setText(Integer.toString(post.getUpvote()));
            downvoteLabel.setText(Integer.toString(post.getDownvote()));
        }

}




