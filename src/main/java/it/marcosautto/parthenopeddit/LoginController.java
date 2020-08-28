package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Button loginButton;

    @FXML
    Label loginResult;

    @FXML
    CheckBox rememberCheckbox;

    Preferences prefs = Preferences.userNodeForPackage(LoginController.class);

    private Main Main;

    public void setMain(Main Main) {
        this.Main = Main;

        // Add observable list data to the table
    }

    @FXML
    private void loginClicked() throws IOException {
        boolean user_logged = false;
        user_logged = validateLogin();
        if(rememberCheckbox.isSelected())
            savePreference();
        else{
            usernameTextField.clear();
            passwordTextField.clear();
        }

        if(user_logged){
            Main.getInstance().userLogged(usernameTextField.getText());
        }
    }

    private boolean validateLogin(){
        //TODO: Login with API

        return true;
    }

    private void savePreference(){
        prefs.putBoolean("remember", rememberCheckbox.isSelected());
        prefs.put("username", usernameTextField.getText());
        prefs.put("password", passwordTextField.getText());        //TODO: encrypt password

    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        rememberCheckbox.setSelected(prefs.getBoolean("remember", false));
        if(rememberCheckbox.isSelected()){
            usernameTextField.setText(prefs.get("username", ""));
            passwordTextField.setText(prefs.get("password", ""));
        }
        // Initialization code can go here.
        // The parameters url and resources can be omitted if they are not needed
    }

    public void showPrivacyDisclaimer() throws IOException {

        Main.getInstance().showPrivacyDisclaimer();

    }

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }*/
}
