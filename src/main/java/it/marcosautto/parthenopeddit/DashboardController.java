package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.state.DashboardState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import it.marcosautto.parthenopeddit.model.User;

import java.io.IOException;
import java.text.ParseException;

public class DashboardController {

    @FXML
    private Pane secondPane;

    @FXML
    private Button homeButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button courseButton;

    @FXML
    private Button groupButton;

    @FXML
    private Button groupInviteButton;

    @FXML
    private Button infoButton;

    @FXML
    private Label usernameLabel;

    private DashboardState dashboard_state;

    private static DashboardController instance;
    public DashboardController() throws IOException { instance = this;

        dashboard_state = new DashboardState();

    }

    private Main Main;
    private CoursePageController CoursePageController;
    private GroupPageController GroupPageController;
    private PostPageController PostPageController;
    private ReviewPageController ReviewPageController;
    private ProfileController ProfileController;
    private NewPostController NewPostController;
    private NewReviewController NewReviewController;
    private GroupInviteMemberController GroupInviteMemberController;

    public void setMain(Main Main) {
        this.Main = Main;

    }

    public static DashboardController getInstance() {
        return instance;
    }

    //STATE PATTERN

    public void homeFXML(ActionEvent event) throws IOException {
        dashboard_state.home(secondPane, homeButton);
    }

    public void searchFXML(ActionEvent event) throws IOException {
        dashboard_state.search(secondPane, searchButton);
    }

    public void profileFXML(ActionEvent event) throws IOException, InterruptedException {
        profileSelected(Auth.getInstance().getUsername());
    }

    public void courseFXML(ActionEvent event) throws IOException {
        dashboard_state.course(secondPane, courseButton);
    }

    public void groupFXML(ActionEvent event) throws IOException {
        dashboard_state.group(secondPane, groupButton);
    }

    public void groupInviteFXML(ActionEvent event) throws IOException {
        dashboard_state.groupInvite(secondPane, groupInviteButton);
    }

    public void infoFXML(ActionEvent event) throws IOException {
        dashboard_state.info(secondPane, infoButton);
    }

    public void outDashboardFXML(String ui) throws IOException{
        dashboard_state.outDashboard(secondPane, ui);
    }

    @FXML
    private void handleLogout() throws IOException {
        Main.getInstance().userLogout();
    }

    public void transferMessage(String message) {
        usernameLabel.setText(message);
    }

   public void courseSelected(int courseId) throws IOException, InterruptedException {
       outDashboardFXML("/CoursePageLayout.fxml");
       CoursePageController.getInstance().transferMessage(courseId);
   }

   public void groupSelected(int groupId) throws IOException, InterruptedException, ParseException {
       outDashboardFXML("/GroupPageLayout.fxml");
       GroupPageController.getInstance().transferMessage(groupId);
   }

   public void postSelected(int postId) throws IOException, InterruptedException, ParseException {
       outDashboardFXML("/PostPageLayout.fxml");
        PostPageController.getInstance().transferMessage(postId);
    }

    public void reviewSelected(int reviewId, String courseName) throws IOException, InterruptedException {
        outDashboardFXML("/ReviewPageLayout.fxml");
        ReviewPageController.getInstance().transferMessage(reviewId, courseName);
    }

    public void profileSelected(String userId) throws IOException, InterruptedException {
        dashboard_state.profile(secondPane, profileButton);
        ProfileController.getInstance().transferMessage(userId);
    }

    public void createNewGroup() throws IOException {
        outDashboardFXML("/NewGroupLayout.fxml");
    }

    public void publishPost(int boardId, String boardName) throws IOException {
        outDashboardFXML("/NewPostLayout.fxml");
        NewPostController.getInstance().transferMessage(boardId, boardName);
    }

    public void writeReview(int courseId) throws IOException, InterruptedException {
        outDashboardFXML("/NewReviewLayout.fxml");
        NewReviewController.getInstance().transferMessage(courseId);
    }

    public void inviteUser(int groupId, String groupName) throws IOException, InterruptedException {
        outDashboardFXML("/GroupInviteMemberLayout.fxml");
        GroupInviteMemberController.getInstance().transferMessage(groupId, groupName);
    }

}
