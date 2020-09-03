package it.marcosautto.parthenopeddit.searchPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.PostListViewController;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchPostController implements Initializable {

    @FXML
    private ListView<Post> postListView;

    private ObservableList<Post> postObservableList;

    private static SearchPostController instance;

    public static SearchPostController getInstance() {
        return instance;
    }

    public SearchPostController()  {

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
            postListView.setPlaceholder(new Label("Non sono stati trovati post."));

        if(posts.isEmpty()){
            postListView.getItems().clear();
        }
    }
}
