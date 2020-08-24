package sample;

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
import sample.api.Mockdatabase;
import sample.model.GroupInvite;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GroupInviteController implements Initializable {

    @FXML
    private ListView<GroupInvite> groupInviteListView;

    private ObservableList<GroupInvite> inviteObservableList;

    private DashboardController dashboardController;

    private Mockdatabase Mockdatabase;

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;

        // Add observable list data to the table
    }

    public GroupInviteController(){

        inviteObservableList = FXCollections.observableArrayList();

        //Mockdatabase.getInstance().user1.getGroupInvites();

        //add some Students
        inviteObservableList.addAll(
                Mockdatabase.getInstance().user1.getGroupInvites());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupInviteListView.setItems(inviteObservableList);
        groupInviteListView.setCellFactory(postListView -> new GroupInviteListViewController());

        groupInviteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Your action here
            System.out.println("Selected item: " + newValue.getGroup().getName());

            //DashboardController dashboardController = new DashboardController();

            try {
                //dashboardController.getInstance().inviteSelected(newValue.getGroupInviteId());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Invito gruppo");
                alert.setHeaderText("Vuoi entrare nel gruppo " + newValue.getGroup().getName() + "?");
                alert.setContentText("Seleziona un'opzione.");
                alert.setResizable(false);

                ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType buttonNo = new ButtonType("No");
                ButtonType buttonYes = new ButtonType("Si");

                alert.getButtonTypes().setAll(buttonCancel, buttonNo, buttonYes);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonYes){
                    //TODO: API add user in group
                    Mockdatabase.getInstance().user1.addGroup(newValue.getGroup());
                    Mockdatabase.getInstance().groups_table.stream().filter(group -> group.getId() == newValue.getGroupId()).collect(Collectors.toList()).get(0).addGroupMember(Mockdatabase.getInstance().user1);
                    DashboardController.getInstance().groupFXML(null);
                } else if (result.get() == buttonNo) {
                    //TODO: API remove groupInvite
                } else {
                    alert.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
