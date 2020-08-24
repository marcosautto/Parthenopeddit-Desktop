package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.api.Mockdatabase;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label usernameShownLabel;

    @FXML
    private Button changeUsernameButton;

    @FXML
    private Button showUserPostButton;

    @FXML
    private Button showUserReviewButton;

    private DashboardController DashboardController;

    private User user;

    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;

        // Add observable list data to the table
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        user = Mockdatabase.getInstance().user1;

        usernameShownLabel.setText(user.getDisplayName());
        usernameLabel.setText(user.getId());

    }

    public void showUserPost() throws IOException {
        DashboardController.getInstance().showUserPost(user);
    }

    public void showUserReview() throws IOException {
        DashboardController.getInstance().showUserReview(user);
    }
}
