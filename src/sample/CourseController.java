package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.Api.Mockdatabase;
import sample.model.Course;
import sample.model.Group;
import sample.model.GroupInvite;
import sample.model.GroupMember;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    private ListView<Course> courseListView;

    private ObservableList<Course> courseObservableList;

    private DashboardController dashboardController;

    private Mockdatabase mockdatabase;

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;

        // Add observable list data to the table
    }

    public CourseController(){

        courseObservableList = FXCollections.observableArrayList();

        Mockdatabase.getInstance().user1.getFollowedCourses();

        //add some Students
        courseObservableList.addAll(
                Mockdatabase.getInstance().user1.getFollowedCourses());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseListView.setItems(courseObservableList);
        courseListView.setCellFactory(postListView -> new CourseListViewController());

        courseListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {

            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue.getName());

                //DashboardController dashboardController = new DashboardController();

                try {
                    dashboardController.getInstance().courseSelected(newValue.getCourseId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
