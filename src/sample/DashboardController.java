package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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

    public void setMain(Main Main) {
        this.Main = Main;

        // Add observable list data to the table
    }

    public void homeFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/HomeLayout.fxml");
    }

    public void profileFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/ProfileLayout.fxml");
    }

    public void courseFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/CourseLayout.fxml");
    }

    public void groupFXML(ActionEvent event) throws IOException {
        loadFxml(event, "fxml/DashboardMenu/GroupLayout.fxml");
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

    public static DashboardController getInstance() {
        return instance;
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

        //GroupPageController.getInstance().transferMessage(groupId);
   }

    public void writeReview(int courseId) throws IOException {

        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("fxml/NewReviewLayout.fxml"));
        secondPane.getChildren().setAll(node);

        NewReviewController.getInstance().transferMessage(courseId);
    }

}
