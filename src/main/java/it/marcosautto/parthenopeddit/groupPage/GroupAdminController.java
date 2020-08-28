package it.marcosautto.parthenopeddit.groupPage;

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

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GroupAdminController implements Initializable {

    @FXML
    private ListView<GroupMember> adminListView;

    private ObservableList<GroupMember> adminObservableList;


    private static GroupAdminController instance;
    // static method to get instance of view
    public static GroupAdminController getInstance() {
        return instance;
    }

    public GroupAdminController()  {

        instance = this;
        adminObservableList = FXCollections.observableArrayList();
        Mockdatabase mockdatabase = new Mockdatabase();

        //add some Students
        adminObservableList.addAll(
        );


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //postListView.setItems(postObservableList);
        //postListView.setCellFactory(postListView -> new PostListViewController());
        adminListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GroupMember>() {

            @Override
            public void changed(ObservableValue<? extends GroupMember> observable, GroupMember oldValue, GroupMember newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue.getUserId());

                //DashboardController dashboardController = new DashboardController();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Amministratore selezionato");
                alert.setHeaderText("Cosa vuoi fare con l'amministratore " + newValue.getUserId() + "?");
                alert.setContentText("Seleziona un'opzione.");

                ButtonType buttonAdmin= new ButtonType("Revoca amministratore");
                ButtonType buttonKick = new ButtonType("Caccia dal gruppo");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonKick, buttonAdmin, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonAdmin){
                    //TODO: API make admin as user
                } else if (result.get() == buttonKick) {
                    //TODO: kick admin from group
                }  else {
                    // ... admin chose CANCEL or closed the dialog
                }
            }
        });

    }

    public void sendAdmins(ObservableList<GroupMember> admins){
        adminListView.setItems(admins);
        adminListView.setCellFactory(postListView -> new GroupMemberListViewController());
    }
}
