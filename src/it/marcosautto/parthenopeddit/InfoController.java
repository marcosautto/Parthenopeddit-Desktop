package it.marcosautto.parthenopeddit;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InfoController {

    public void sendMailToMs() throws URISyntaxException{
            String subject="Parthenopeddit";

            try {
                Desktop.getDesktop().mail( new URI( "mailto:marco.sautto@gmail.com?subject="+subject) );
            }
            catch ( IOException ex )
            {
            }
    }

    public void sendMailToFb() throws URISyntaxException{
        String subject="Parthenopeddit";

        try {
            Desktop.getDesktop().mail( new URI( "mailto:francesco.bottino001@studenti.uniparthenope.it?subject="+subject) );
        }
        catch ( IOException ex )
        {
        }
    }

    public void openMsGitHubWebpage() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/marcosautto"));
    }

    public void openFbGitHubWebpage() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/FrancescoBottino"));
    }

    public void openJfxWebpage() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://www.jfoenix.com"));
    }

    public void openJfxGitHubWebpage()throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/jfoenixadmin/JFoenix"));
    }

    public void openCfxWebpage() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://fxexperience.com/controlsfx/"));
    }

    public void openCfxGitHubWebpage()throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/controlsfx/controlsfx"));
    }
}
