package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.GroupsRequests;
import it.marcosautto.parthenopeddit.factory.AlertFactory;
import it.marcosautto.parthenopeddit.factory.AlertType;
import it.marcosautto.parthenopeddit.model.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.GroupInvite;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GroupInviteController implements Initializable {

    @FXML
    private ListView<GroupInvite> groupInviteListView;

    private ObservableList<GroupInvite> inviteObservableList;

    private ObservableList<GroupInvite> group_invites;

    private DashboardController dashboardController;

    private GroupsRequests GroupsRequests;

    private AlertFactory alertFactory;

    private Mockdatabase Mockdatabase;

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;

        // Add observable list data to the table
    }

    public GroupInviteController(){

        inviteObservableList = FXCollections.observableArrayList();
        GroupsRequests = new GroupsRequests();
        alertFactory = new AlertFactory();

        ////group_invite = Mockdatabase.getInstance().user1.getGroupInvites();
//
        ////add some Students
        //if(group_invite.size() > 0)
        //    inviteObservableList.addAll(
        //            group_invite);
        //else
        //    groupInviteListView.setPlaceholder(new Label("Non hai ricevuto alcun invito."));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            group_invites = GroupsRequests.getUserInvitesToGroup();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if(group_invites.size() > 0){
            groupInviteListView.setItems(group_invites);
            groupInviteListView.setCellFactory(postListView -> new GroupInviteListViewController(true));
        } else
            groupInviteListView.setPlaceholder(new Label("Non hai ricevuto alcun invito."));



        groupInviteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Your action here
            System.out.println("Selected item: " + newValue.getGroup().getName());

            //DashboardController dashboardController = new DashboardController();

            try {
                //dashboardController.getInstance().inviteSelected(newValue.getGroupInviteId());

                //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //alert.setTitle("Invito gruppo");
                //alert.setHeaderText("Vuoi entrare nel gruppo " + newValue.getGroup().getName() + "?");
                //alert.setContentText("Seleziona un'opzione.");
                //alert.setResizable(false);
//
                //ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                //ButtonType buttonNo = new ButtonType("No");
                //ButtonType buttonYes = new ButtonType("Si");
//
                //alert.getButtonTypes().setAll(buttonCancel, buttonNo, buttonYes);
                AlertType alert = alertFactory.getAlert("INVITE_ALERT", newValue.getGroup().getName());
                Optional<ButtonType> result = alert.getResult();

                if (result.get().getText().equals("Si")){
                    GroupsRequests.answerGroupInvite(newValue.getGroupId(), true);
                    DashboardController.getInstance().groupSelected(newValue.getGroupId());
                } else if (result.get().getText().equals("No")) {
                    GroupsRequests.answerGroupInvite(newValue.getGroupId(), false);
                    DashboardController.getInstance().groupInviteFXML(null);
                } else {
                    alert.closeAlert();
                }


            } catch (IOException | InterruptedException | ParseException e) {
                e.printStackTrace();
            }
        });

    }
}
