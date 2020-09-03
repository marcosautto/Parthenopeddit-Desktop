package it.marcosautto.parthenopeddit.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class GroupMemberAlert extends AlertType {

    private Alert alert;

    public GroupMemberAlert(String userId){
        createAlert(userId);
    }

    @Override
    public void createAlert(String userId) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Utente selezionato");
        alert.setHeaderText("Cosa vuoi fare con l'utente " + userId + "?");
        alert.setContentText("Seleziona un'opzione.");

        ButtonType buttonAdmin= new ButtonType("Rendi amministratore");
        ButtonType buttonKick = new ButtonType("Caccia dal gruppo");
        ButtonType buttonProfile = new ButtonType("Visualizza profilo");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonProfile, buttonKick, buttonAdmin, buttonTypeCancel);

        super.setAlert(alert);
    }
}
