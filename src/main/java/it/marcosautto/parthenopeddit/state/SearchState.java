package it.marcosautto.parthenopeddit.state;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SearchState implements State {

    private final DashboardState dashboardState;

    public SearchState(DashboardState dashboardState){
        this.dashboardState = dashboardState;
    }

    @Override
    public void home(Pane pane, Button button) throws IOException {
        dashboardState.setDashboardState(dashboardState.getHomeState());

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("/DashboardMenu/HomeLayout.fxml"));
        pane.getChildren().setAll(node);
    }

    @Override
    public void search(Pane pane, Button button) throws IOException {
    }

    @Override
    public void profile(Pane pane, Button button) throws IOException {
        dashboardState.setDashboardState(dashboardState.getProfileState());

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("/DashboardMenu/ProfileLayout.fxml"));
        pane.getChildren().setAll(node);
    }

    @Override
    public void course(Pane pane, Button button) throws IOException {
        dashboardState.setDashboardState(dashboardState.getCourseState());

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("/DashboardMenu/CourseLayout.fxml"));
        pane.getChildren().setAll(node);
    }

    @Override
    public void group(Pane pane, Button button) throws IOException {
        dashboardState.setDashboardState(dashboardState.getGroupState());

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("/DashboardMenu/GroupLayout.fxml"));
        pane.getChildren().setAll(node);
    }

    @Override
    public void groupInvite(Pane pane, Button button) throws IOException {
        dashboardState.setDashboardState(dashboardState.getGroupInviteState());

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("/DashboardMenu/GroupInviteLayout.fxml"));
        pane.getChildren().setAll(node);
    }

    @Override
    public void info(Pane pane, Button button) throws IOException {
        dashboardState.setDashboardState(dashboardState.getInfoState());

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("/DashboardMenu/InfoLayout.fxml"));
        pane.getChildren().setAll(node);
    }

    @Override
    public void outDashboard(Pane pane, String ui) throws IOException {
        dashboardState.setDashboardState(dashboardState.getInfoState());

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource(ui));
        pane.getChildren().setAll(node);
    }
}
