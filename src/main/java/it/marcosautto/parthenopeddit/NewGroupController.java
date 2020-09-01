package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.api.GroupsRequests;
import it.marcosautto.parthenopeddit.api.PostsRequests;
import it.marcosautto.parthenopeddit.model.Group;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NewGroupController {

    private Auth Auth;

    private GroupsRequests GroupsRequests;

    @FXML
    private TextField groupnameTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    private int boardId;

    private it.marcosautto.parthenopeddit.api.PostsRequests PostsRequests = new PostsRequests(Auth.getInstance());

    private static NewGroupController instance;
    public NewGroupController() {
        instance = this;
        GroupsRequests = new GroupsRequests();
    }

    public static NewGroupController getInstance() {
        return instance;
    }


    public void handleCreateGroup() throws IOException, InterruptedException {
        Group group = GroupsRequests
                .createGroup(
                        groupnameTextField
                                .getText(),
                        Collections.<String> emptyList());
        DashboardController.getInstance().groupSelected(group.getId());
    }

    public void handleCancel() throws IOException{
        DashboardController.getInstance().groupFXML(null);
    }
}
