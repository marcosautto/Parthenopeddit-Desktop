package it.marcosautto.parthenopeddit.state;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public interface State {

    public void home(Pane pane, Button button) throws IOException;
    public void search(Pane pane, Button button) throws IOException;
    public void profile(Pane pane, Button button) throws IOException;
    public void course(Pane pane, Button button) throws IOException;
    public void group(Pane pane, Button button) throws IOException;
    public void groupInvite(Pane pane, Button button) throws IOException;
    public void info(Pane pane, Button button) throws IOException;
    public void outDashboard(Pane pane, String ui) throws IOException;
}
