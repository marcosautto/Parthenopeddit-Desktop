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

    private Mockdatabase Mockdatabase;

    @FXML
    private void buttonClick(){
        shoesLabel.setVisible(true);


    }

    public HomeController()  {

        postObservableList = FXCollections.observableArrayList();
        Mockdatabase mockdatabase = new Mockdatabase();

        //add some Students
        postObservableList.addAll(
                Mockdatabase.getInstance().posts_table
        );


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeListView.setItems(postObservableList);
        homeListView.setCellFactory(postListView -> new PostListViewController());

    }
}
