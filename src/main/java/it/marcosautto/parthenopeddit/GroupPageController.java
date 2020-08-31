package it.marcosautto.parthenopeddit;

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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    public ArrayList<GroupMember> groupMembers;
    public ArrayList<GroupMember> groupAdmins;
    public ArrayList<GroupMember> groupUsers;

    public boolean followed = false;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();
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

    }

    public void transferMessage(int group_Id) {

        group = Mockdatabase.getInstance().groups_table.stream().filter(group -> group.getId() == group_Id).collect(toList()).get(0);
       //groupMembers = group.getMembers();
       //groupAdmins = group.getMembers().stream().filter(groupMember -> groupMember.getIsOwner() == true).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableList(l)));
       //groupUsers = group.getMembers().stream().filter(groupMember -> groupMember.getIsOwner() == false).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableList(l)));

        groupId = group.getId();
        groupNameTitleLabel.setText(group.getName());
        createdOnLabel.setText(group.getCreatedOn());
        adminLabel.setText(Integer.toString(groupAdmins.size()));
        memberLabel.setText(Integer.toString(groupMembers.size()));

        if(groupAdmins.stream().anyMatch(admin -> admin.getUserId() == "marcosautto")){
            inviteButton.setDisable(false);
        } else{
            inviteButton.setDisable(true);
        }

        GroupPostController.getInstance().sendPosts(group.getPosts());
        GroupAdminController.getInstance().sendAdmins(groupAdmins);
        GroupUserController.getInstance().sendUsers(groupUsers);

    }

    public void handleLeave() throws IOException {
        if(groupMembers.stream().anyMatch(user -> user.getUserId() == "marcosautto")){

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
                DashboardController.getInstance().groupFXML(null);
            }
            //oke button is pressed
            else if(result.get() == ButtonType.CANCEL)
                alert.close();
            // cancel button is pressed

        }
    }

    public void handleInvite(){
        if(groupAdmins.stream().anyMatch(admin -> admin.getUserId() == "user1")){
            //Invite
        }
    }
}
