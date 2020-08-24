package sample.groupPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.UserListViewController;
import sample.api.Mockdatabase;
import sample.model.GroupMember;

import java.net.URL;
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

    }

    public void sendAdmins(ObservableList<GroupMember> admins){
        adminListView.setItems(admins);
        adminListView.setCellFactory(postListView -> new UserListViewController());
    }
}
