package it.marcosautto.parthenopeddit.profilePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.CommentListViewController;
import it.marcosautto.parthenopeddit.model.Comment;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCommentController implements Initializable {

    private static UserCommentController instance;

    public static UserCommentController getInstance() {
        return instance;
    }

    @FXML
    private ListView<Comment> commentListView;

    private ObservableList<Comment> commentObservableList;

    public UserCommentController() {

        instance = this;
        commentObservableList = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendComments(ObservableList<Comment> comments) {
        if (comments.size() > 0) {
            commentListView.setItems(comments);
            commentListView.setCellFactory(commentListView -> new CommentListViewController());
        } else
            commentListView.setPlaceholder(new Label("Non hai ancora pubblicato alcun commento."));
    }
}
