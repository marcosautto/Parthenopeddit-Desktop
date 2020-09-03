package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.ApiClient;
import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.api.AuthRequests;
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
    Label loginResultLabel;

    @FXML
    CheckBox rememberCheckbox;

    Preferences prefs = Preferences.userNodeForPackage(LoginController.class);

    private Main Main;

    public void setMain(Main Main) {
        this.Main = Main;
    }

    @FXML
    private void loginClicked() throws IOException, InterruptedException {

        if(usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            loginResultLabel.setText("Devi inserire username e password.");
            loginResultLabel.setVisible(true);
        } else {
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
    }

    private boolean validateLogin() throws IOException, InterruptedException {
        Auth auth = new Auth(usernameTextField.getText(), passwordTextField.getText());

        AuthRequests AuthRequests = new AuthRequests();

        int return_code = AuthRequests.login(auth.getInstance().getToken());

        if(return_code == 200 || return_code == 201){
            return true;
        } else if(return_code == 452){
            loginResultLabel.setText("Username o password errati.");
            loginResultLabel.setVisible(true);
        } else{
            System.out.println(return_code);
        }

        return false;
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
    }

    public void showPrivacyDisclaimer() throws IOException {

        Main.getInstance().showPrivacyDisclaimer();

    }

}
