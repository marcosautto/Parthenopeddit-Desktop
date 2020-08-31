package it.marcosautto.parthenopeddit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import it.marcosautto.parthenopeddit.model.User;

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
    private it.marcosautto.parthenopeddit.api.Mockdatabase Mockdatabase;
    private CoursePageController CoursePageController;
    private GroupPageController GroupPageController;
    private PostPageController PostPageController;
    private ReviewPageController ReviewPageController;
    private ProfileController ProfileController;
    private NewPostController NewPostController;
    private NewReviewController NewReviewController;

    public void setMain(Main Main) {
        this.Main = Main;

        // Add observable list data to the table
    }

    public static DashboardController getInstance() {
        return instance;
    }

    public void homeFXML(ActionEvent event) throws IOException {
        loadFxml(event, "/DashboardMenu/HomeLayout.fxml");
    }

    public void searchFXML(ActionEvent event) throws IOException {
        loadFxml(event, "/DashboardMenu/SearchLayout.fxml");
    }

    public void profileFXML(ActionEvent event) throws IOException {
        //loadFxml(event, "fxml/DashboardMenu/ProfileLayout.fxml");
        profileSelected("marcosautto");
    }

    public void courseFXML(ActionEvent event) throws IOException {
        loadFxml(event, "/DashboardMenu/CourseLayout.fxml");
    }

    public void groupFXML(ActionEvent event) throws IOException {
        loadFxml(event, "/DashboardMenu/GroupLayout.fxml");
    }

    public void groupInviteFXML(ActionEvent event) throws IOException {
        loadFxml(event, "/DashboardMenu/GroupInviteLayout.fxml");
    }

    public void infoFXML(ActionEvent event) throws IOException {
        loadFxml(event, "/DashboardMenu/InfoLayout.fxml");
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

   public void courseSelected(int courseId) throws IOException, InterruptedException {
       Node node;
       node = (Node)FXMLLoader.load(getClass().getResource("/CoursePageLayout.fxml"));
       secondPane.getChildren().setAll(node);

       CoursePageController.getInstance().transferMessage(courseId);
   }

   public void groupSelected(int groupId) throws IOException{
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/GroupPageLayout.fxml"));
        secondPane.getChildren().setAll(node);

        GroupPageController.getInstance().transferMessage(groupId);
   }

   public void postSelected(int postId) throws IOException, InterruptedException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/PostPageLayout.fxml"));
        secondPane.getChildren().setAll(node);

        PostPageController.getInstance().transferMessage(postId);
    }

    public void reviewSelected(int reviewId) throws IOException{
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/ReviewPageLayout.fxml"));
        secondPane.getChildren().setAll(node);

        ReviewPageController.getInstance().transferMessage(reviewId);
    }

    public void profileSelected(String userId) throws IOException{
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/DashboardMenu/ProfileLayout.fxml"));
        secondPane.getChildren().setAll(node);

        ProfileController.getInstance().transferMessage(userId);
    }

    public void publishPost(int boardId, String boardName) throws IOException {

        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/NewPostLayout.fxml"));
        secondPane.getChildren().setAll(node);
        System.out.println("dbc "+boardId);

        NewPostController.getInstance().transferMessage(boardId, boardName);
    }

    public void writeReview(int courseId) throws IOException, InterruptedException {

        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/NewReviewLayout.fxml"));
        secondPane.getChildren().setAll(node);

        NewReviewController.getInstance().transferMessage(courseId);
    }

    public void showUserPost(User user) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/UserPostLayout.fxml"));
        secondPane.getChildren().setAll(node);
    }

    public void showUserReview(User user) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/UserReviewLayout.fxml"));
        secondPane.getChildren().setAll(node);
    }

}
