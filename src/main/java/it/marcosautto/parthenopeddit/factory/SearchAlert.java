package it.marcosautto.parthenopeddit.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class SearchAlert extends AlertType {

    private Alert alert;

    public SearchAlert(){
        createAlert("");
    }

    @Override
    public void createAlert(String groupName){
        alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText("La ricerca deve essere lunga almeno 3 caratteri.");
        alert.show();
        super.setAlert(alert);
    }
}
