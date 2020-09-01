package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.CoursesRequests;
import it.marcosautto.parthenopeddit.api.GroupsRequests;
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
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SearchController implements Initializable {

    private CoursesRequests CoursesRequests;
    private GroupsRequests GroupsRequests;

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

    private it.marcosautto.parthenopeddit.api.Mockdatabase Mockdatabase;

    private DashboardController DashboardController;

    private static SearchController instance;
    // static method to get instance of view
    public static SearchController getInstance() {
        return instance;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.DashboardController = dashboardController;

        // Add observable list data to the table
    }

    public SearchController()  {
        instance = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        FXMLLoader loader = new FXMLLoader();

        CoursesRequests = new CoursesRequests();
        GroupsRequests = new GroupsRequests();

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


        ObservableList<Course> foundCourses = CoursesRequests.searchByName(queryTextField.getText());
        //ObservableList<Post> foundPosts = (ObservableList<Post>) Mockdatabase.getInstance().posts_table.stream().filter(post -> post.getTitle().contains(query)).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableArrayList(l)));
        //ObservableList<User> foundUsers = (ObservableList<User>) Mockdatabase.getInstance().users_table.stream().filter(user -> user.getDisplayName().contains(query)).collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableArrayList(l)));



        SearchCourseController.getInstance().sendCourses(foundCourses);
        //SearchPostController.getInstance().sendPosts(foundPosts);
        //SearchUserController.getInstance().sendUsers(foundUsers);
    }


}
