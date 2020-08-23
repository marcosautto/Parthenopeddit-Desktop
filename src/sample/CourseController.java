package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.model.Course;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    @FXML
    private ListView<Course> courseListView;

    private ObservableList<Course> courseObservableList;

    private DashboardController dashboardController;

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;

        // Add observable list data to the table
    }

    public CourseController(){

        courseObservableList = FXCollections.observableArrayList();

        //add some Students
        courseObservableList.addAll(
                new Course("Programmazione 3", 3.5, 2.0, 0),
                new Course("Terminali Mobili e Multimedialità", 4.5, 1.5, 1)
        );

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
