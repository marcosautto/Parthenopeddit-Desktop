package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import sample.groupPage.GroupAdminController;
import sample.groupPage.GroupPostController;
import sample.groupPage.GroupUserController;
import sample.model.Group;
import sample.model.GroupMember;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class GroupPageController implements Initializable {

    private static GroupPageController instance;
    public GroupPageController() { instance = this; }
    // static method to get instance of view
    public static GroupPageController getInstance() {
        return instance;
    }

    private DashboardController DashboardController;

    private sample.api.Mockdatabase Mockdatabase;


    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;

        // Add observable list data to the table
    }

    @FXML
    public TabPane tabPane;

    @FXML
    public Tab postTab;

    @FXML
    public GroupPostController groupPostController;

    @FXML
    public Tab adminTab;

    @FXML
    public GroupAdminController groupAdminController;

    @FXML
    public Tab userTab;

    @FXML
    public GroupUserController groupUserController;

    @FXML
    public Label groupNameTitleLabel;

    @FXML
    public Label createdOnLabel;

    @FXML
    public Label adminLabel;

    @FXML
    public Label memberLabel;

    @FXML
    public Button leaveButton;

    @FXML
    public Button inviteButton;

    public int groupId;
    
    public Group group;

    public ObservableList<GroupMember> groupMembers;
    public ObservableList<GroupMember> groupAdmins;
    public ObservableList<GroupMember> groupUsers;

    public boolean followed = false;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();
        try
        {
            AnchorPane anch1 = loader.load(getClass().getResource("fxml/GroupPostLayout.fxml"));
            postTab.setContent(anch1);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab1");
        }

        loader = new FXMLLoader();
        try{
            AnchorPane anch2 = loader.load(getClass().getResource("fxml/GroupAdminLayout.fxml"));
            adminTab.setContent(anch2);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab2");
        }
        loader = new FXMLLoader();
        try{
            AnchorPane anch3 = loader.load(getClass().getResource("fxml/GroupUserLayout.fxml"));
            userTab.setContent(anch3);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab3");
        }


    }

    public void transferMessage(int group_Id) {

        group = Mockdatabase.getInstance().groups_table.stream().filter(group -> group.getId() == group_Id).collect(toList()).get(0);
        groupMembers = group.getMembers();
        groupAdmins = group.getMembers().stream().filter(groupMember -> groupMember.getIsOwner() == true).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableList(l)));
        groupUsers = group.getMembers().stream().filter(groupMember -> groupMember.getIsOwner() == false).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableList(l)));

        groupId = group.getId();
        groupNameTitleLabel.setText(group.getName());
        createdOnLabel.setText(group.getCreatedOn());
        adminLabel.setText(Integer.toString(groupAdmins.size()));
        memberLabel.setText(Integer.toString(groupMembers.size()));

        GroupPostController.getInstance().sendPosts(group.getPosts());
        GroupAdminController.getInstance().sendAdmins(groupAdmins);
        GroupUserController.getInstance().sendUsers(groupUsers);

    }

    public void handleLeave(){

    }

    public void handleInvite(){
        
    }
}
