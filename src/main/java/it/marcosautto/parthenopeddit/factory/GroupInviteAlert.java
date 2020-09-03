package it.marcosautto.parthenopeddit.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class GroupInviteAlert extends AlertType {

    private Alert alert;

    public GroupInviteAlert(String groupName){
        createAlert(groupName);
    }

    @Override
    public void createAlert(String groupName){
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Invito gruppo");
        alert.setHeaderText("Vuoi entrare nel gruppo " + groupName + "?");
        alert.setContentText("Seleziona un'opzione.");
        alert.setResizable(false);

        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonNo = new ButtonType("No");
        ButtonType buttonYes = new ButtonType("Si");

        alert.getButtonTypes().setAll(buttonCancel, buttonNo, buttonYes);

        super.setAlert(alert);
    }
}
