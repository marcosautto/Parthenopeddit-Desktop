package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.GroupInvite;

import java.io.IOException;

public class GroupInviteListViewController extends ListCell<GroupInvite> {

    @FXML
    private Label groupnameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(GroupInvite invite, boolean empty) {
        super.updateItem(invite, empty);

        if(empty || invite == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/ListCell/GroupInviteListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            groupnameLabel.setText(invite.getGroup().getName());
            usernameLabel.setText(invite.getInviterId());

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
