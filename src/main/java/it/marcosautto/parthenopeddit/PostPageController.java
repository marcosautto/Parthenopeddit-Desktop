package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.CommentsRequests;
import it.marcosautto.parthenopeddit.api.PostsRequests;
import it.marcosautto.parthenopeddit.api.UserRequests;
import it.marcosautto.parthenopeddit.util.DateParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Comment;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.api.Auth;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PostPageController implements Initializable {

    private static PostPageController instance;
    public PostPageController() { instance = this; }
    // static method to get instance of view
    public static PostPageController getInstance() {
        return instance;
    }

    private DashboardController DashboardController;

    private it.marcosautto.parthenopeddit.api.Mockdatabase Mockdatabase;

    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;

        // Add observable list data to the table
    }
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
    private AnchorPane postAnchorPane;

    @FXML
    private TextArea commentTextArea;

    @FXML
    private Button sendCommentButton;

    @FXML
    private ListView<Comment> commentListView;

    private ObservableList<Comment> commentObservableList;

    private Auth Auth;

    private PostsRequests PostsRequests;

    private CommentsRequests CommentsRequests;

    private DateParser DateParser;



    private Post post;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PostsRequests = new PostsRequests();
        CommentsRequests = new CommentsRequests();
        DateParser = new DateParser();
    }

    public void transferMessage(int post_id) throws IOException, InterruptedException, ParseException {
        System.out.println(post_id);

        //-----POST-----
        post = PostsRequests.getPostWithComments(post_id);

        System.out.println(post.getId());
        System.out.println(post.getComments().size());


        titleLabel.setText(post.getTitle());
        authorLabel.setText(post.getAuthorId());
        boardLabel.setText(post.getPostedToBoard().getName());
        timestampLabel.setText(DateParser.parseDate(post.getTimestamp()));
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
            if(post.getPostedToBoardId() == 1) {
                try {
                    DashboardController.getInstance().homeFXML(null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(post.getPostedToBoard().getType() == "course") {
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

        upvoteButton.setOnMouseClicked(e ->{
            try {
                PostsRequests.likePost(post.getId());
                updateVotes();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        });

        downvoteButton.setOnMouseClicked(e ->{
            try {
                PostsRequests.dislikePost(post.getId());
                updateVotes();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        //-----COMMENT LIST-----

        commentObservableList = FXCollections.observableArrayList();

        if(post.getCommentsNum() > 0){
            commentObservableList.addAll(
                 post.getComments()
            );
            System.out.println(post.getComments().size());
        commentListView.setItems(commentObservableList);
        commentListView.setCellFactory(postListView -> new CommentListViewController());
        } else
            commentListView.setPlaceholder(new Label("Non ci sono commenti per questo post."));

    }

    public void sendComment() throws IOException, InterruptedException {
        if(!commentTextArea.getText().isEmpty()){
            CommentsRequests.publishNewComment(commentTextArea.getText(), post.getId());
            //Mockdatabase.getInstance().posts_table.stream().filter(post -> post.getId() == this.post.getId()).collect(Collectors.toList()).get(0).addComment(comment);
            commentTextArea.clear();

            post = PostsRequests.getPostWithComments(post.getId());
            ObservableList<Comment> comments = FXCollections.observableList(post.getComments());
            commentListView.setItems(comments);
            commentListView.setCellFactory(postListView -> new CommentListViewController());

        }
    }

    private void updateVotes() throws IOException, InterruptedException {
        post = PostsRequests.getPost(post.getId());
        upvoteLabel.setText(Integer.toString(post.getUpvote()));
        downvoteLabel.setText(Integer.toString(post.getDownvote()));

    }


}

