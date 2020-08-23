package sample.coursePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.Mockdatabase;
import sample.PostListViewController;
import sample.model.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class CoursePostController implements Initializable {

    @FXML
    private ListView<Post> postListView;

    private ObservableList<Post> postObservableList;

    private static CoursePostController instance;
    // static method to get instance of view
    public static CoursePostController getInstance() {
        return instance;
    }

    public CoursePostController()  {

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
