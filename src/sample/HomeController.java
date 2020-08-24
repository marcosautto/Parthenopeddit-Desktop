package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.api.Mockdatabase;
import sample.model.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    Button button;

    @FXML
    Label shoesLabel;

    @FXML
    private ListView<Post> homeListView;

    private ObservableList<Post> postObservableList;

    @FXML
    private void buttonClick(){
        shoesLabel.setVisible(true);


    }

    public HomeController()  {

        postObservableList = FXCollections.observableArrayList();
        Mockdatabase mockdatabase = new Mockdatabase();

        //add some Students
        postObservableList.addAll(
                new Post("John Doe", Post.CONTENT.HOME, mockdatabase.user1, "This is the first post",0),
                new Post("Jane Doe", Post.CONTENT.HOME, mockdatabase.user2, "Second post here", 0),
                new Post("Donte Dunigan", Post.CONTENT.GROUP, mockdatabase.user1, "LOLOLOLOLOLOLOLOLOLOLOLLOLOLOLOLOLOLOLOLLOLOLOLOLOLOLOLOLLO LOLOOLOLOLOLOLLLOLOLOLOLOLOLOLOLOLOLOLOLOLOLLOLOLOLOLOLOLOLOLLOLOLOL OLOLOLOLOLLOLOLOOLOLOLOLOLLL OLOLOLOLOLOLOLOLO LOLOLOLOLOL LOLO LOLOLOLOLOLOLLOLOLOLOLOLOLOLOLLOL OLOOLOLOLOLOLLLOLOLO2", 0),
                new Post("Gavin Genna", Post.CONTENT.COURSE, mockdatabase.user2, "heilà", 0),
                new Post("Darin Dear", Post.CONTENT.GROUP, mockdatabase.user1, "Shazam", 0),
                new Post("Pura Petty", Post.CONTENT.HOME, mockdatabase.user2, "Lana del rey", 0),
                new Post("Herma Hines", Post.CONTENT.HOME, mockdatabase.user1, "Optimus prime", 0)
        );


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeListView.setItems(postObservableList);
        homeListView.setCellFactory(postListView -> new PostListViewController());

    }
}
