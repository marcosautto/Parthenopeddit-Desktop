package it.marcosautto.parthenopeddit.state;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardState implements State {

    private State homeState;
    private State searchState;
    private State profileState;
    private State courseState;
    private State groupState;
    private State groupInviteState;
    private State infoState;
    private State outDashboardState;
    private State state;

    private Button buttonSelected;

    public DashboardState(){
        this.homeState = new HomeState(this);
        this.searchState = new SearchState(this);
        this.profileState = new ProfileState(this);
        this.courseState = new CourseState(this);
        this.groupState = new GroupState(this);
        this.groupInviteState = new GroupInviteState(this);
        this.infoState = new InfoState(this);
        this.outDashboardState = new OutdashboardState(this);

        this.state = outDashboardState;     //Default state
        buttonSelected = new Button();
    }

    public void setDashboardState(State state){
        this.state = state;
    }

    public State getHomeState(){ return homeState; }
    public State getSearchState(){ return searchState; }
    public State getProfileState(){ return profileState; }
    public State getCourseState(){ return courseState; }
    public State getGroupState(){ return groupState; }
    public State getGroupInviteState(){ return groupInviteState; }
    public State getInfoState(){ return infoState; }

    @Override
    public void home(Pane pane, Button button) throws IOException {
        setButtonSelected(button);
        state.home(pane, button);
    }

    @Override
    public void search(Pane pane, Button button) throws IOException {
        setButtonSelected(button);
        state.search(pane, button);
    }

    @Override
    public void profile(Pane pane, Button button) throws IOException {
        setButtonSelected(button);
        state.profile(pane, button);
    }

    @Override
    public void course(Pane pane, Button button) throws IOException {
        setButtonSelected(button);
        state.course(pane, button);
    }

    @Override
    public void group(Pane pane, Button button) throws IOException {
        setButtonSelected(button);
        state.group(pane, button);
    }

    @Override
    public void groupInvite(Pane pane, Button button) throws IOException {
        setButtonSelected(button);
        state.groupInvite(pane, button);
    }

    @Override
    public void info(Pane pane, Button button) throws IOException {
        setButtonSelected(button);
        state.info(pane, button);
    }

    @Override
    public void outDashboard(Pane pane, String ui) throws IOException {
        this.buttonSelected.setStyle("-fx-background-color: #FFFFFF");
        state.outDashboard(pane, ui);
    }

    public void setButtonSelected(Button button){
        this.buttonSelected.setStyle("-fx-background-color: #FFFFFF");
        this.buttonSelected = button;
        this.buttonSelected.setStyle("-fx-background-color: #a3a3a3");
    }
}
