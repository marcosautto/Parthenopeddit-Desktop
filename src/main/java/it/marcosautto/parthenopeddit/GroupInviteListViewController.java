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

    public boolean flag;

    private FXMLLoader mLLoader;

    public GroupInviteListViewController(boolean flag){
        this.flag = flag;
    }

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

            if(flag)                        //Se è vero, setta il nome del gruppo (Usato per GroupInviteController)
                groupnameLabel.setText(invite.getGroup().getName());
            else                            //Altrimenti, setta il nome dell'utente invitato (Usato per GroupInvitedController)
                groupnameLabel.setText(invite.getInvitedId());
            usernameLabel.setText(invite.getInviterId());

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
