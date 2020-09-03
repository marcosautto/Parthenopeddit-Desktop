package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.api.Auth;
import it.marcosautto.parthenopeddit.api.PostsRequests;
import it.marcosautto.parthenopeddit.model.Group;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class NewPostController {

        @FXML
        private Label boardNameLabel;

        @FXML
        private TextField titleTextField;

        @FXML
        private TextArea bodyTextArea;

        @FXML
        private Button cancelButton;

        @FXML
        private Button publishButton;

        private int boardId;

        private PostsRequests PostsRequests = new PostsRequests();

        private static NewPostController instance;
        public NewPostController() {
            instance = this;
        }

        public static NewPostController getInstance() {
            return instance;
        }

        public void transferMessage(int boardId, String boardName) {
            this.boardId = boardId;
            System.out.println("npc "+boardId);
            System.out.println(boardId + " " + boardName);
            boardNameLabel.setText(boardName);
        }

        public void handlePublish() throws IOException, InterruptedException {
            System.out.println("handle publish "+boardId);

            if(titleTextField.getText().length()<3){
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("Il titolo del post deve essere lungo almeno 3 caratteri.");
                alert.show();
            } else {
                int result_code = PostsRequests.publishNewPost(titleTextField.getText(), bodyTextArea.getText(), boardId);
                System.out.println(result_code);
                DashboardController.getInstance().homeFXML(null);
            }
        }

        public void handleCancel() throws IOException{
            DashboardController.getInstance().homeFXML(null);
        }

}
