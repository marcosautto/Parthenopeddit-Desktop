package it.marcosautto.parthenopeddit.groupPage;

import it.marcosautto.parthenopeddit.DashboardController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.GroupMemberListViewController;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.GroupMember;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GroupAdminController implements Initializable {

    @FXML
    private ListView<GroupMember> adminListView;

    private ObservableList<GroupMember> adminObservableList;

    private static GroupAdminController instance;

    public static GroupAdminController getInstance() {
        return instance;
    }

    public GroupAdminController()  {

        instance = this;
        adminObservableList = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GroupMember>() {

            @Override
            public void changed(ObservableValue<? extends GroupMember> observable, GroupMember oldValue, GroupMember newValue) {
                try {
                    DashboardController.getInstance().profileSelected(newValue.getUserId());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void sendAdmins(ObservableList<GroupMember> admins){
        adminObservableList.addAll(
        admins);
        adminListView.setItems(adminObservableList);
        adminListView.setCellFactory(postListView -> new GroupMemberListViewController());
    }
}
