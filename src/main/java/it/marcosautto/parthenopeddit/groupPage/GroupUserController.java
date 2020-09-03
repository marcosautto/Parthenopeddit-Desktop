package it.marcosautto.parthenopeddit.groupPage;

import it.marcosautto.parthenopeddit.DashboardController;
import it.marcosautto.parthenopeddit.GroupPageController;
import it.marcosautto.parthenopeddit.api.GroupsRequests;
import it.marcosautto.parthenopeddit.factory.AlertFactory;
import it.marcosautto.parthenopeddit.factory.AlertType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import it.marcosautto.parthenopeddit.GroupMemberListViewController;
import it.marcosautto.parthenopeddit.model.GroupMember;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class GroupUserController implements Initializable {

    @FXML
    private ListView<GroupMember> userListView;

    private ObservableList<GroupMember> userObservableList;

    public GroupsRequests GroupsRequests;

    private GroupPageController GroupPageController;

    private AlertFactory alertFactory;

    private static GroupUserController instance;
    // static method to get instance of view
    public static GroupUserController getInstance() {
        return instance;
    }

    public GroupUserController()  {

        instance = this;
        userObservableList = FXCollections.observableArrayList();
        GroupsRequests = new GroupsRequests();
        alertFactory = new AlertFactory();

        //add some Students
        userObservableList.addAll(
        );


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //postListView.setItems(postObservableList);
        //postListView.setCellFactory(postListView -> new PostListViewController());

        userListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GroupMember>() {

            @Override
            public void changed(ObservableValue<? extends GroupMember> observable, GroupMember oldValue, GroupMember newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue.getUserId());

                //DashboardController dashboardController = new DashboardController();
                if(GroupPageController.getInstance().checkIsAdmin()){           //If user is admin
                    //AlertType adminAlert = new AlertType();
//
                    //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    //alert.setTitle("Utente selezionato");
                    //alert.setHeaderText("Cosa vuoi fare con l'utente " + newValue.getUserId() + "?");
                    //alert.setContentText("Seleziona un'opzione.");
//
                    //ButtonType buttonAdmin= new ButtonType("Rendi amministratore");
                    //ButtonType buttonKick = new ButtonType("Caccia dal gruppo");
                    //ButtonType buttonProfile = new ButtonType("Visualizza profilo");
                    //ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
//
                    //alert.getButtonTypes().setAll(buttonProfile, buttonKick, buttonAdmin, buttonTypeCancel);

                    //FACTORY PATTERN
                    AlertType alert = alertFactory.getAlert("MEMBER_ALERT", newValue.getUserId());
                    Optional<ButtonType> result = alert.getResult();

                    if(result.get().getText().equals("Visualizza profilo")){
                        try {
                            DashboardController.getInstance().profileSelected(newValue.getUserId());
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (result.get().getText().equals("Rendi amministratore")){                           //Make user admin
                        try {
                            List<String> user_id = Arrays.asList(newValue.getUserId());
                            GroupsRequests.makeMembersOwners(newValue.getGroupId(), user_id);
                            DashboardController.getInstance().groupSelected(newValue.getGroupId());
                        } catch (IOException | InterruptedException | ParseException e) {
                            e.printStackTrace();
                        }
                    } else if (result.get().getText().equals("Caccia dal gruppo")) {
                        try {
                            GroupsRequests.removeFromGroup(newValue.getGroupId(), newValue.getUserId());     //Remove user from group
                            DashboardController.getInstance().groupSelected(newValue.getGroupId());
                        } catch (IOException | InterruptedException | ParseException e) {
                            e.printStackTrace();
                        }
                    }  else {
                        //Dialog cancel
                    }
                } else{             //Else show user profile
                    try {
                        DashboardController.getInstance().profileSelected(newValue.getUserId());
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    public void sendUsers(ObservableList<GroupMember> users){
        if(users.size() > 0){
            userObservableList.addAll(
            users);
            userListView.setItems(userObservableList);
            userListView.setCellFactory(postListView -> new GroupMemberListViewController());
        } else
            userListView.setPlaceholder(new Label("Non ci sono utenti in questo gruppo."));
    }
}
