package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.*;
import it.marcosautto.parthenopeddit.factory.AlertFactory;
import it.marcosautto.parthenopeddit.factory.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Course;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.model.User;
import it.marcosautto.parthenopeddit.searchPage.SearchPostController;
import it.marcosautto.parthenopeddit.searchPage.SearchUserController;
import it.marcosautto.parthenopeddit.searchPage.SearchCourseController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SearchController implements Initializable {

    private CoursesRequests CoursesRequests;
    private PostsRequests PostsRequests;
    private UserRequests UserRequests;
    private GroupsRequests GroupsRequests;
    private AlertFactory alertFactory;

    @FXML
    private TextField queryTextField;

    @FXML
    private Button searchButton;

    @FXML
    public TabPane tabPane;

    @FXML
    public Tab courseTab;

    @FXML
    public Tab postTab;

    @FXML
    public Tab userTab;

    @FXML
    public SearchCourseController SearchCourseController;

    @FXML
    public SearchPostController SearchPostController;

    @FXML
    public SearchUserController SearchUserController;

    private DashboardController DashboardController;

    private static SearchController instance;

    public SearchController()  {
        instance = this;
    }

    public static SearchController getInstance() {
        return instance;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();

        CoursesRequests = new CoursesRequests();
        PostsRequests = new PostsRequests();
        UserRequests = new UserRequests();
        GroupsRequests = new GroupsRequests();
        alertFactory = new AlertFactory();

        try
        {
            AnchorPane anch1 = loader.load(getClass().getResource("/SearchCourseLayout.fxml"));
            courseTab.setContent(anch1);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab1");
        }

        loader = new FXMLLoader();
        try{
            AnchorPane anch2 = loader.load(getClass().getResource("/SearchPostLayout.fxml"));
            postTab.setContent(anch2);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab2");
        }
        loader = new FXMLLoader();
        try{
            AnchorPane anch3 = loader.load(getClass().getResource("/SearchUserLayout.fxml"));
            userTab.setContent(anch3);
        }
        catch(IOException iex)
        {
            System.out.println("unable to load tab3");
        }

    }

    public void search() throws IOException, InterruptedException {

        String query = queryTextField.getText();
        if(query.length()<3){
            //FACTORY PATTERN
            AlertType alert = alertFactory.getAlert("QUERY_LENGTH_ALERT", "");
        } else{
            ObservableList<Course> foundCourses = CoursesRequests.searchByName(queryTextField.getText());
            ObservableList<Post> foundPosts = PostsRequests.searchPost(queryTextField.getText());
            ObservableList<User> foundUsers = UserRequests.searchUser(queryTextField.getText());

            SearchCourseController.getInstance().sendCourses(foundCourses);
            SearchPostController.getInstance().sendPosts(foundPosts);
            SearchUserController.getInstance().sendUsers(foundUsers);
        }

    }


}
