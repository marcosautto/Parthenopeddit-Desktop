package it.marcosautto.parthenopeddit.searchPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import it.marcosautto.parthenopeddit.DashboardController;
import it.marcosautto.parthenopeddit.UserListViewController;
import it.marcosautto.parthenopeddit.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchUserController implements Initializable {

    @FXML
    private ListView<User> userListView;

    private ObservableList<User> userObservableList;

    private DashboardController DashboardController;

    private static SearchUserController instance;

    public static SearchUserController getInstance() {
        return instance;
    }

    public SearchUserController() {

        instance = this;
        userObservableList = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                try {
                    DashboardController.getInstance().profileSelected(newValue.getId());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void sendUsers(ObservableList<User> users) {
        if (users.size() > 0) {
            userListView.setItems(users);
            userListView.setCellFactory(postListView -> new UserListViewController());
        } else
            userListView.setPlaceholder(new Label("Non sono stati trovati utenti."));

        if(users.isEmpty()){
            userListView.getItems().clear();
        }
    }
}
