package it.marcosautto.parthenopeddit.groupPage;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupPostController implements Initializable {

    @FXML
    private ListView<Post> postListView;

    private ObservableList<Post> postObservableList;

    private static GroupPostController instance;
    // static method to get instance of view
    public static GroupPostController getInstance() {
        return instance;
    }

    public GroupPostController()  {

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
            postObservableList.addAll(
                    posts
            );
            postListView.setCellFactory(postListView -> new PostListViewController());
        } else
            postListView.setPlaceholder(new Label("Non ci sono post in questo gruppo."));
    }
}