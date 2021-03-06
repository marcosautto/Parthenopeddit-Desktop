package it.marcosautto.parthenopeddit.coursePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.PostListViewController;
import java.net.URL;
import java.util.ResourceBundle;

public class CoursePostController implements Initializable {

    @FXML
    private ListView<Post> postListView;

    private ObservableList<Post> postObservableList;

    private static CoursePostController instance;

    public static CoursePostController getInstance() {
        return instance;
    }

    public CoursePostController()  {

        instance = this;
        postObservableList = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendPosts(ObservableList<Post> posts){
        if(posts.size() > 0){
            postListView.setItems(posts);
            postListView.setCellFactory(postListView -> new PostListViewController());
        } else
            postListView.setPlaceholder(new Label("Non ci sono post in questo corso."));
    }
}
