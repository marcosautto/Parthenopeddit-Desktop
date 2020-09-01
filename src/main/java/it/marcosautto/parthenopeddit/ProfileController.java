package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.api.UserRequests;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.profilePage.UserPostController;
import it.marcosautto.parthenopeddit.profilePage.UserReviewController;
import it.marcosautto.parthenopeddit.profilePage.UserCommentController;
import it.marcosautto.parthenopeddit.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProfileController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label usernameShownLabel;

    @FXML
    public TabPane tabPane;

    @FXML
    public Tab postTab;

    @FXML
    public Tab commentTab;

    @FXML
    public Tab reviewTab;

    @FXML
    public UserPostController UserPostController;

    @FXML
    public UserReviewController UserReviewController;

    @FXML
    public UserCommentController UserCommentController;

    @FXML
    private Button changeUsernameButton;

    @FXML
    private Button changeProfilePicButton;

    private UserRequests UserRequests;

    private Mockdatabase Mockdatabase;

    private DashboardController DashboardController;

    private User user;

    private static ProfileController instance;
    // static method to get instance of view
    public static ProfileController getInstance() {
        return instance;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;

        // Add observable list data to the table
    }

    public ProfileController()  {
        instance = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();
        UserRequests = new UserRequests();

        try
        {
            AnchorPane anch1 = loader.load(getClass().getResource("/UserPostLayout.fxml"));
            postTab.setContent(anch1);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab1");
        }

        loader = new FXMLLoader();
        try{
            AnchorPane anch2 = loader.load(getClass().getResource("/UserCommentLayout.fxml"));
            commentTab.setContent(anch2);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab2");
        }
        loader = new FXMLLoader();
        try{
            AnchorPane anch3 = loader.load(getClass().getResource("/UserReviewLayout.fxml"));
            reviewTab.setContent(anch3);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab3");
        }

    }

    public void transferMessage(String userId) throws IOException, InterruptedException {
        user = UserRequests.getUserByID(userId);

        if(user.getId().equals((Auth.getInstance().getUsername()))){
            changeUsernameButton.setVisible(true);
            changeProfilePicButton.setVisible(true);
        } else {
            changeUsernameButton.setVisible(false);
            changeProfilePicButton.setVisible(false);
        }

        if(user.getDisplayName() != null)
            usernameShownLabel.setText(user.getDisplayName());
        else
            usernameShownLabel.setText(user.getId());
        usernameLabel.setText(user.getId());


       UserPostController.getInstance().sendPosts(UserRequests.getUserPublishedPosts(userId, 1, 10, null));
       UserReviewController.getInstance().sendReviews(UserRequests.getUserPublishedReviews(userId, 1, 10, null));
       UserCommentController.getInstance().sendComments(UserRequests.getUserPublishedComments(userId, 1, 10, null));

    }

    public void changeUserimage(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText("Work in progress");
        alert.show();
    }
}
