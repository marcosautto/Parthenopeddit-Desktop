package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.CoursesRequests;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.Course;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    private ListView<Course> courseListView;

    private ObservableList<Course> courseObservableList;

    private ObservableList<Course> followed_courses;

    private DashboardController dashboardController;

    private CoursesRequests CoursesRequests;

    private Mockdatabase mockdatabase;

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;

        // Add observable list data to the table
    }

    public CourseController(){

        courseObservableList = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CoursesRequests = new CoursesRequests();

        try {
            followed_courses = CoursesRequests.getFollowedCourses();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if(followed_courses.size() > 0)
                courseObservableList.addAll(
                        followed_courses);
            else
                courseListView.setPlaceholder(new Label("Non segui alcun corso."));
            courseListView.setItems(CoursesRequests.getFollowedCourses());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        courseListView.setCellFactory(postListView -> new CourseListViewController());

        courseListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {

            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue.getName());

                //DashboardController dashboardController = new DashboardController();

                try {
                    dashboardController.getInstance().courseSelected(newValue.getCourseId());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
