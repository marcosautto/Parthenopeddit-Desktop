package it.marcosautto.parthenopeddit;

import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrivacyController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openAPIWebpage() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://api.uniparthenope.it/"));
    }
}
