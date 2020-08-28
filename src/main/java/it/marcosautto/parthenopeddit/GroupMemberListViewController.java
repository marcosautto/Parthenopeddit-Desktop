package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.GroupMember;
import it.marcosautto.parthenopeddit.model.Post;

import java.io.IOException;

public class GroupMemberListViewController extends ListCell<GroupMember> {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label joinedOnLabel;

    @FXML
    private AnchorPane anchorPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(GroupMember user, boolean empty) {
        super.updateItem(user, empty);

        if(empty || user == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("fxml/ListCell/GroupMemberListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            usernameLabel.setText(user.getUserId());
            joinedOnLabel.setText(user.getJoinDate());

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
