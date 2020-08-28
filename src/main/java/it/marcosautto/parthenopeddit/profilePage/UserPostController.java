package it.marcosautto.parthenopeddit.profilePage;

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

public class UserPostController implements Initializable {

    @FXML
    private ListView<Post> postListView;

    private ObservableList<Post> postObservableList;

    private static UserPostController instance;
    // static method to get instance of view
    public static UserPostController getInstance() {
        return instance;
    }

    public UserPostController()  {

        instance = this;
        postObservableList = FXCollections.observableArrayList();
        Mockdatabase mockdatabase = new Mockdatabase();

        //add some Students
        postObservableList.addAll(
        );


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //postListView.setItems(postObservableList);
        //postListView.setCellFactory(postListView -> new PostListViewController());

    }

    public void sendPosts(ObservableList<Post> posts){
        if(posts.size() > 0){
            postListView.setItems(posts);
            postListView.setCellFactory(postListView -> new PostListViewController());
        } else
            postListView.setPlaceholder(new Label("Non ci sono post in questo gruppo."));
    }
}