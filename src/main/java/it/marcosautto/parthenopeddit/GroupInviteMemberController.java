package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.api.GroupsRequests;
import it.marcosautto.parthenopeddit.model.Group;
import it.marcosautto.parthenopeddit.model.GroupMember;
import it.marcosautto.parthenopeddit.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class GroupInviteMemberController implements Initializable {

    @FXML
    private Label groupnameLabel;

    @FXML
    private TextField queryTextField;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<User> userListview;

    private ObservableList<User> userObservableList;

    private ObservableList<User> invitable_users;

    private Auth Auth;

    private GroupsRequests GroupsRequests;

    private int groupId;

    private String groupName;

    private static GroupInviteMemberController instance;
    public GroupInviteMemberController() {
        instance = this;
        userObservableList = FXCollections.observableArrayList();

    }

    public static GroupInviteMemberController getInstance() {
        return instance;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GroupsRequests = new GroupsRequests();
    }

    public void transferMessage(int groupId, String groupName) throws IOException, InterruptedException {
        this.groupId = groupId;
        this.groupName = groupName;
        groupnameLabel.setText(groupName);
    }

    public void searchUser() throws IOException, InterruptedException {
        invitable_users = GroupsRequests.searchInvitableUser(groupId, queryTextField.getText());


        if(invitable_users.size() > 0)
            userObservableList.addAll(
                    invitable_users);
        else
            userListview.setPlaceholder(new Label("Non è stato trovato alcun utente."));

        userListview
                .setItems(
                        userObservableList);
        userListview.setCellFactory(userListview -> new UserListViewController());

        userListview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue.getId());

                //DashboardController dashboardController = new DashboardController();

                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Invita utente");
                    alert.setHeaderText("Vuoi invitare " + newValue.getId() + " nel gruppo " + groupName + "?");
                    alert.setResizable(false);
                    alert.setContentText("Seleziona un'opzione.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(!result.isPresent()) {
                        alert.close();
                    }
                    // alert is exited, no button has been pressed.
                    else if(result.get() == ButtonType.OK){
                        List<String> user_id = Arrays.asList(newValue.getId());
                        GroupsRequests.inviteUsersToGroup(groupId, user_id);
                        DashboardController.getInstance().groupSelected(groupId);
                    }
                    //oke button is pressed
                    else if(result.get() == ButtonType.CANCEL)
                        alert.close();
                    // cancel button is pressed
                } catch (IOException | InterruptedException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
