package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.model.Course;
import sample.model.Group;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {

    @FXML
    private ListView<Group> groupListView;

    private ObservableList<Group> groupObservableList;

    public GroupController(){

        groupObservableList = FXCollections.observableArrayList();

        //add some Students
        groupObservableList.addAll(
                new Group("CS Memes", "22/08/2020"),
                new Group("Studenti L-21", "10/08/2017")
        );

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupListView.setItems(groupObservableList);
        groupListView.setCellFactory(postListView -> new GroupListViewController());

    }
}
