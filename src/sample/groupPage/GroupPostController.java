package sample.groupPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.PostListViewController;
import sample.api.Mockdatabase;
import sample.model.Post;

import java.net.URL;
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
        postListView.setItems(posts);
        postListView.setCellFactory(postListView -> new PostListViewController());
    }
}