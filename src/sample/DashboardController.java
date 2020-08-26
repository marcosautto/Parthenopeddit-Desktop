package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.model.User;
import sample.profilePage.UserPostController;
import sample.profilePage.UserReviewController;

import java.io.IOException;

public class DashboardController {

    @FXML
    Pane secondPane;

    @FXML
    Label usernameLabel;

    private static DashboardController instance;
    public DashboardController() {
        instance = this;
    }

    private Main Main;
    private sample.api.Mockdatabase Mockdatabase;
    private CoursePageController CoursePageController;
    private GroupPageController GroupPageController;
    private PostPageController PostPageController;
    private ReviewPageController ReviewPageController;
    private ProfileController ProfileController;

    public void setMain(Main Main) {
        this.Main = Main;

        // Add observable list data to the table
    }

    public static DashboardController getInstance() {
        return instance;
    }

    public void homeFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/HomeLayout.fxml");
    }

    public void searchFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/SearchLayout.fxml");
    }

    public void profileFXML(ActionEvent event) throws IOException {
        //loadFxml(event, "fxml/DashboardMenu/ProfileLayout.fxml");
        profileSelected("marcosautto");
    }

    public void courseFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/CourseLayout.fxml");
    }

    public void groupFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/GroupLayout.fxml");
    }

    public void groupInviteFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/GroupInviteLayout.fxml");
    }

    public void infoFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/InfoLayout.fxml");
    }

    private void loadFxml (ActionEvent event, String ui) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource(ui));
        secondPane.getChildren().setAll(node);
    }

    @FXML
    private void handleLogout() throws IOException {
        Main.getInstance().userLogout();
    }

    public void transferMessage(String message) {
        //Display the message
        usernameLabel.setText(message);
    }

   public void courseSelected(int courseId) throws IOException {
       Node node;
       node = (Node)FXMLLoader.load(getClass().getResource("fxml/CoursePageLayout.fxml"));
       secondPane.getChildren().setAll(node);

       CoursePageController.getInstance().transferMessage(courseId);
   }

   public void groupSelected(int groupId) throws IOException{
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/GroupPageLayout.fxml"));
        secondPane.getChildren().setAll(node);

        GroupPageController.getInstance().transferMessage(groupId);
   }

   public void postSelected(int postId) throws IOException{
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/PostPageLayout.fxml"));
        secondPane.getChildren().setAll(node);

        PostPageController.getInstance().transferMessage(postId);
    }

    public void reviewSelected(int reviewId) throws IOException{
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/ReviewPageLayout.fxml"));
        secondPane.getChildren().setAll(node);

        ReviewPageController.getInstance().transferMessage(reviewId);
    }

    public void profileSelected(String userId) throws IOException{
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/DashboardMenu/ProfileLayout.fxml"));
        secondPane.getChildren().setAll(node);

        ProfileController.getInstance().transferMessage(userId);
    }

    public void writeReview(int courseId) throws IOException {

        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/NewReviewLayout.fxml"));
        secondPane.getChildren().setAll(node);

        NewReviewController.getInstance().transferMessage(courseId);
    }

    public void showUserPost(User user) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/UserPostLayout.fxml"));
        secondPane.getChildren().setAll(node);
    }

    public void showUserReview(User user) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/UserReviewLayout.fxml"));
        secondPane.getChildren().setAll(node);
    }

}
