package it.marcosautto.parthenopeddit.groupPage;

import it.marcosautto.parthenopeddit.DashboardController;
import it.marcosautto.parthenopeddit.GroupInviteListViewController;
import it.marcosautto.parthenopeddit.GroupPageController;
import it.marcosautto.parthenopeddit.PostListViewController;
import it.marcosautto.parthenopeddit.api.GroupsRequests;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.GroupInvite;
import it.marcosautto.parthenopeddit.model.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;

public class GroupInvitedController implements Initializable {

    @FXML
    private ListView<GroupInvite> groupInviteListView;

    private ObservableList<GroupInvite> postObservableList;

    private GroupsRequests GroupsRequests;

    private static GroupInvitedController instance;

    public static GroupInvitedController getInstance() {
        return instance;
    }

    public GroupInvitedController()  {

        instance = this;
        GroupsRequests = new GroupsRequests();
        postObservableList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        groupInviteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {
                if(GroupPageController.getInstance().checkIsAdmin()){               //If user is admin
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Invito gruppo");
                    alert.setHeaderText("Cosa vuoi fare con l'invito di " + newValue.getInvitedId() + "?");
                    alert.setContentText("Seleziona un'opzione.");
                    alert.setResizable(false);

                    ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    ButtonType buttonNo = new ButtonType("Annulla invito");
                    ButtonType buttonYes = new ButtonType("Visualizza profilo");

                    alert.getButtonTypes().setAll(buttonCancel, buttonNo, buttonYes);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonYes){
                        DashboardController.getInstance().profileSelected(newValue.getInvitedId());
                    } else if (result.get() == buttonNo) {
                        GroupsRequests.undoInvite(newValue.getGroupId(), newValue.getInvitedId());
                        DashboardController.getInstance().groupSelected(newValue.getGroupId());
                    } else {
                        alert.close();
                    }
                } else{

                }

            } catch (IOException | InterruptedException | ParseException e) {
                e.printStackTrace();
            }
        });

    }

    public void sendInvitedUsers(ObservableList<GroupInvite> group_invites){
        if(group_invites.size() > 0){
            postObservableList.addAll(group_invites);
            groupInviteListView.setItems(postObservableList);
            groupInviteListView.setCellFactory(postListView -> new GroupInviteListViewController(false));
        } else
            groupInviteListView.setPlaceholder(new Label("Non ci sono utenti invitati."));
    }
}
