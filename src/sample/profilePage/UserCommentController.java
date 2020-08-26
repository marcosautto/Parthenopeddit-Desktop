package sample.profilePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.CommentListViewController;
import sample.model.Comment;

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

    private sample.api.Mockdatabase Mockdatabase;


    public UserCommentController() {

        instance = this;
        commentObservableList = FXCollections.observableArrayList();

        commentObservableList.addAll();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //commentListView.setItems(commentObservableList);
        //commentListView.setCellFactory(commentListView -> new CommentListViewController());

    }

    public void sendComments(ObservableList<Comment> comments) {
        if (comments.size() > 0) {
            commentListView.setItems(comments);
            commentListView.setCellFactory(commentListView -> new CommentListViewController());
        } else
            commentListView.setPlaceholder(new Label("Non hai ancora pubblicato alcun commento."));
    }
}
