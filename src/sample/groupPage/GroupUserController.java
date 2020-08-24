package sample.groupPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.PostListViewController;
import sample.UserListViewController;
import sample.api.Mockdatabase;
import sample.model.GroupMember;
import sample.model.Post;

import java.net.URL;
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

    }

    public void sendUsers(ObservableList<GroupMember> users){
        userListView.setItems(users);
        userListView.setCellFactory(postListView -> new UserListViewController());
    }
}
