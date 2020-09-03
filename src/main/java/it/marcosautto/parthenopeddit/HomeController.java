package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.api.UserRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import it.marcosautto.parthenopeddit.api.Mockdatabase;
import it.marcosautto.parthenopeddit.model.Post;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private Auth Auth;

    @FXML
    private ListView<Post> homeListView;

    @FXML
    private Label numPostLabel;

    @FXML
    private Label pageLabel;

    @FXML
    private Button publishPostButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private Button nextPageButton;

    private ObservableList<Post> postObservableList;

    private Mockdatabase Mockdatabase;

    private DashboardController DashboardController;

    private UserRequests UserRequests;

    private int page = 1;

    public HomeController()  {

        postObservableList = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserRequests = new UserRequests();

        try {
            previousPageButton.setDisable(true);
            homeListView.setItems(UserRequests.getUserFeed(1, 10, null));
            pageLabel.setText("Pagina 1");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        homeListView.setCellFactory(postListView -> new PostListViewController());
    }

    public void publishPost() throws IOException {
        DashboardController.getInstance().publishPost(0, "Home");
    }

    public void nextPage() throws IOException, InterruptedException {
        previousPageButton.setDisable(false);
        ObservableList<Post> posts = UserRequests.getUserFeed(++page, 10, null);
        if(!posts.isEmpty()){
            homeListView.setItems(posts);
            pageLabel.setText("Pagina " + page);
        } else {
            nextPageButton.setDisable(true);
        }
    }

    public void previousPage() throws IOException, InterruptedException {
        if(page==2) {
            previousPageButton.setDisable(true);
        }
        if(nextPageButton.isDisabled())
            nextPageButton.setDisable(false);
        homeListView.setItems(UserRequests.getUserFeed(--page, 10, null));
        pageLabel.setText("Pagina " + page);
    }
}
