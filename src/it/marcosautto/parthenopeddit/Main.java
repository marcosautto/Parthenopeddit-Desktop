package it.marcosautto.parthenopeddit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import it.marcosautto.parthenopeddit.api.Mockdatabase;

import java.io.IOException;

public class Main extends Application {

    Stage loginStage;
    //Stage privacyStage;
    Stage dashboardStage;

    private static Main instance;
    public Main() {
        instance = this;
    }
    // static method to get instance of view
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Mockdatabase mockdatabase = new Mockdatabase();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/LoginLayout.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/Parthenopeddit_logo.png")));
        primaryStage.setTitle("Parthenopeddit Desktop - Login");
        primaryStage.setScene(new Scene(root, 700, 600));
        loginStage = primaryStage;
        primaryStage.show();


    }

    public void userLogged(String username) throws IOException {
        loginStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Dashboard.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        Stage dashboardStage = new Stage();
        dashboardStage.getIcons().add(new Image(getClass().getResourceAsStream("img/Parthenopeddit_logo.png")));
        dashboardStage.setTitle("Parthenopeddit Desktop");
        dashboardStage.setScene(new Scene(root, 1200, 950));
        this.dashboardStage = dashboardStage;
        DashboardController dashboardController = loader.getController();
        dashboardController.transferMessage(username);
        dashboardStage.show();
    }

    public void userLogout() throws IOException {
        dashboardStage.close();
        loginStage.show();
    }

    public void showPrivacyDisclaimer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/PrivacyLayout.fxml"));

        Parent root = loader.load();
        Stage privacyStage = new Stage();
        privacyStage.getIcons().add(new Image(getClass().getResourceAsStream("img/Parthenopeddit_logo.png")));
        privacyStage.setTitle("Parthenopeddit Desktop - Privacy Disclaimer");
        privacyStage.setScene(new Scene(root, 350, 300));
        privacyStage.show();


    }


        public static void main(String[] args) {
        launch(args);
    }
}
