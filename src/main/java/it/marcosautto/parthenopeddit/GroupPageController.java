package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.groupPage.GroupInvitedController;
import it.marcosautto.parthenopeddit.model.GroupInvite;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.util.DateParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.groupPage.GroupAdminController;
import it.marcosautto.parthenopeddit.groupPage.GroupPostController;
import it.marcosautto.parthenopeddit.groupPage.GroupUserController;
import it.marcosautto.parthenopeddit.model.Group;
import it.marcosautto.parthenopeddit.model.GroupMember;
import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.api.GroupsRequests;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
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

    private it.marcosautto.parthenopeddit.api.Mockdatabase Mockdatabase;


    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;

        // Add observable list data to the table
    }

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab postTab;

    @FXML
    private GroupPostController groupPostController;

    @FXML
    private Tab adminTab;

    @FXML
    private GroupAdminController groupAdminController;

    @FXML
    private Tab userTab;

    @FXML
    private GroupUserController groupUserController;

    @FXML
    private Tab groupInviteTab;

    @FXML
    private GroupInvitedController groupInvitedController;

    @FXML
    private Label groupNameTitleLabel;

    @FXML
    private Label createdOnLabel;

    @FXML
    private Label adminLabel;

    @FXML
    private Label memberLabel;

    @FXML
    private Button leaveButton;

    @FXML
    private Button inviteButton;

    private Auth Auth;

    private GroupsRequests GroupsRequests;

    private DateParser DateParser;

    private int groupId;
    
    private Group group;

    private ObservableList<Post> groupPosts;
    private ObservableList<GroupMember> groupMembers;
    private ObservableList<GroupMember> groupAdmins;
    private ObservableList<GroupMember> groupUsers;
    private ObservableList<GroupInvite> groupInvites;

    private boolean isAdmin = false;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();
        GroupsRequests = new GroupsRequests();
        DateParser = new DateParser();
        try
        {
            AnchorPane anch1 = loader.load(getClass().getResource("/GroupPostLayout.fxml"));
            postTab.setContent(anch1);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab1");
        }

        loader = new FXMLLoader();
        try{
            AnchorPane anch2 = loader.load(getClass().getResource("/GroupAdminLayout.fxml"));
            adminTab.setContent(anch2);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab2");
        }
        loader = new FXMLLoader();
        try{
            AnchorPane anch3 = loader.load(getClass().getResource("/GroupUserLayout.fxml"));
            userTab.setContent(anch3);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab3");
        }
        try{
            AnchorPane anch4 = loader.load(getClass().getResource("/GroupInvitedLayout.fxml"));
            groupInviteTab.setContent(anch4);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab4");
        }

    }

    public void transferMessage(int group_Id) throws IOException, InterruptedException, ParseException {

        group = GroupsRequests.getGroup(group_Id);
        groupPosts = GroupsRequests.getGroupPosts(group_Id, 10, 1, null);
        groupMembers = GroupsRequests.getGroupMembers(group_Id);
        groupAdmins = groupMembers.stream().filter(groupMember -> groupMember.getIsOwner() == true).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableList(l)));
        groupUsers = groupMembers.stream().filter(groupMember -> groupMember.getIsOwner() == false).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableList(l)));
        groupInvites = GroupsRequests.getGroupInvites(group_Id);
        groupId = group.getId();
        groupNameTitleLabel.setText(group.getName());
        createdOnLabel.setText(DateParser.parseDate(group.getCreatedOn()));
        adminLabel.setText(Integer.toString(groupAdmins.size()));
        memberLabel.setText(Integer.toString(groupMembers.size()));


       if(groupAdmins.stream().anyMatch(x -> x.getUserId().equals(Auth.getInstance().getUsername()))){
            inviteButton.setDisable(false);
            isAdmin = true;
        } else{
            inviteButton.setDisable(true);
            isAdmin = false;
        }

        GroupPostController.getInstance().sendPosts(groupPosts);
        GroupAdminController.getInstance().sendAdmins(groupAdmins);
        GroupUserController.getInstance().sendUsers(groupUsers);
        GroupInvitedController.getInstance().sendInvitedUsers(groupInvites);

    }

    public void handleLeave() throws IOException, InterruptedException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Lascia gruppo");
        alert.setHeaderText("Vuoi davvero lasciare il gruppo " + group.getName() + "?");
        alert.setResizable(false);
        alert.setContentText("Seleziona un'opzione.");
        Optional<ButtonType> result = alert.showAndWait();
        if(!result.isPresent()) {
            alert.close();
        }
        // alert is exited, no button has been pressed.
        else if(result.get() == ButtonType.OK){
            //TODO: API remove user from group
            GroupsRequests.leaveGroup(groupId);
            DashboardController.getInstance().groupFXML(null);
        }
        //oke button is pressed
        else if(result.get() == ButtonType.CANCEL)
            alert.close();
        // cancel button is pressed
    }

    public void writePost() throws IOException {
        DashboardController.getInstance().publishPost(groupId, group.getName());
    }

    public void handleInvite() throws IOException, InterruptedException {
        DashboardController.getInstance().inviteUser(groupId, group.getName());
    }

    public boolean checkIsAdmin(){ return isAdmin; }

}
