package sample.searchPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.PostListViewController;
import sample.api.Mockdatabase;
import sample.coursePage.CoursePostController;
import sample.model.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchPostController implements Initializable {

    @FXML
    private ListView<Post> postListView;

    private ObservableList<Post> postObservableList;



    private static SearchPostController instance;
    // static method to get instance of view
    public static SearchPostController getInstance() {
        return instance;
    }

    public SearchPostController()  {

        instance = this;
        postObservableList = FXCollections.observableArrayList();
        Mockdatabase mockdatabase = new Mockdatabase();

        //postObservableList.addAll(
        //);


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
            postListView.setPlaceholder(new Label("Non sono stati trovati post."));
    }
}
