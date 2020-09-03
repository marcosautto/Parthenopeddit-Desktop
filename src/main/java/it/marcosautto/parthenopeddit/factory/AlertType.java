package it.marcosautto.parthenopeddit.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class AlertType {
    private Alert alert;

    public void setAlert(Alert alert){ this.alert = alert; }
    public abstract void createAlert(String string);
    public Optional<ButtonType> getResult(){
        return alert.showAndWait();
    }
    public void closeAlert(){ alert.close(); }
}
