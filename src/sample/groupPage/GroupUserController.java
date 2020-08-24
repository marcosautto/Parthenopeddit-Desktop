package sample.groupPage;

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
import sample.PostListViewController;
import sample.UserListViewController;
import sample.api.Mockdatabase;
import sample.model.Course;
import sample.model.GroupMember;
import sample.model.Post;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GroupUserController implements Initializable {

    @FXML
    private ListView<GroupMember> userListView;

    private ObservableList<GroupMember> userObservableList;


    private static GroupUserController instance;
    // static method to get instance of view
    public static GroupUserController getInstance() {
        return instance;
    }

    public GroupUserController()  {

        instance = this;
        userObservableList = FXCollections.observableArrayList();
        Mockdatabase mockdatabase = new Mockdatabase();

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

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Utente selezionato");
                alert.setHeaderText("Cosa vuoi fare con l'utente " + newValue.getUserId() + "?");
                alert.setContentText("Seleziona un'opzione.");

                ButtonType buttonAdmin= new ButtonType("Rendi amministratore");
                ButtonType buttonKick = new ButtonType("Caccia dal gruppo");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonKick, buttonAdmin, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonAdmin){
                    //TODO: API make user as admin
                } else if (result.get() == buttonKick) {
                    //TODO: kick user from group
                }  else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        });


    }

    public void sendUsers(ObservableList<GroupMember> users){
        userListView.setItems(users);
        userListView.setCellFactory(postListView -> new UserListViewController());
    }
}
