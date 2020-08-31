package it.marcosautto.parthenopeddit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.Group;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {

    @FXML
    private ListView<Group> groupListView;

    private ObservableList<Group> groupObservableList;

    private ObservableList<Group> user_group;

    private Mockdatabase Mockdatabase;

    private DashboardController DashboardController;

    public GroupController(){

        groupObservableList = FXCollections.observableArrayList();

        //add some Students
        //user_group = Mockdatabase.getInstance().user1.getGroups();

        if(user_group.size() > 0)
        groupObservableList.addAll(
                user_group);
        else
            groupListView.setPlaceholder(new Label("Non sei iscritto ad alcun gruppo."));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupListView.setItems(groupObservableList);
        groupListView.setCellFactory(postListView -> new GroupListViewController());

        groupListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {

            @Override
            public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue.getName());

                //DashboardController dashboardController = new DashboardController();

                try {
                    DashboardController.getInstance().groupSelected(newValue.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
