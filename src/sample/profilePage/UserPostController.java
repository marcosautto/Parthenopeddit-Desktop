package sample.profilePage;

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

public class UserPostController implements Initializable {

    private static UserPostController instance;

    public static UserPostController getInstance() {
        return instance;
    }

    @FXML
    private ListView<Post> postListView;

    private ObservableList<Post> postObservableList;

    private sample.api.Mockdatabase Mockdatabase;


    public UserPostController()  {

        instance = this;
        postObservableList = FXCollections.observableArrayList();

        postObservableList.addAll( Mockdatabase.getInstance().user1.getPublishedPosts() );

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        postListView.setItems(postObservableList);
        postListView.setCellFactory(postListView -> new PostListViewController());

    }

}

