package it.marcosautto.parthenopeddit.searchPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.CourseListViewController;
import it.marcosautto.parthenopeddit.DashboardController;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.Course;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchCourseController implements Initializable {

    @FXML
    private ListView<Course> courseListView;

    private ObservableList<Course> courseObservableList;

    private ObservableList<Course> followed_courses;

    private DashboardController dashboardController;

    private Mockdatabase mockdatabase;

    private static SearchCourseController instance;
    // static method to get instance of view
    public static SearchCourseController getInstance() {
        return instance;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;

        // Add observable list data to the table
    }

    public SearchCourseController(){

        instance = this;

        courseObservableList = FXCollections.observableArrayList();

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
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void sendCourses(ObservableList<Course> courses){
        if(courses.size() > 0){
            courseListView.setItems(courses);
            courseListView.setCellFactory(postListView -> new CourseListViewController());
        } else
            courseListView.setPlaceholder(new Label("Non sono stati trovati corsi."));
    }
}

