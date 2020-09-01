package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.GroupsRequests;
import it.marcosautto.parthenopeddit.model.GroupMember;
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
import it.marcosautto.parthenopeddit.api.Auth;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {

    @FXML
    private ListView<GroupMember> groupListView;

    private ObservableList<GroupMember> groupObservableList;

    private ObservableList<GroupMember> user_group;

    private Auth Auth;
    private GroupsRequests GroupsRequests;

    private Mockdatabase Mockdatabase;

    private DashboardController DashboardController;

    public GroupController() throws IOException, InterruptedException {

        groupObservableList = FXCollections.observableArrayList();




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GroupsRequests = new GroupsRequests();

        try {
            user_group = GroupsRequests.getUserGroups();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(user_group.size() > 0)
            groupObservableList.addAll(
                    user_group);
        else
            groupListView.setPlaceholder(new Label("Non sei iscritto ad alcun gruppo."));

        groupListView.setItems(groupObservableList);
        groupListView.setCellFactory(postListView -> new GroupListViewController());

        groupListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GroupMember>() {

            @Override
            public void changed(ObservableValue<? extends GroupMember> observable, GroupMember oldValue, GroupMember newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue.getGroup().getName());

                //DashboardController dashboardController = new DashboardController();

                try {
                    DashboardController.getInstance().groupSelected(newValue.getGroup().getId());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void createNewGroup() throws IOException {
        DashboardController.getInstance().createNewGroup();
    }
}
