package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.model.GroupMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Group;

import java.io.IOException;

public class GroupListViewController extends ListCell<GroupMember> {

        @FXML
        private Label groupNameLabel;

        @FXML
        private Label createdOnLabel;
        
        @FXML
        private AnchorPane anchorPane;

        private FXMLLoader mLLoader;
        
        @Override
        protected void updateItem(GroupMember group, boolean empty) {
                super.updateItem(group, empty);
        
                if(empty || group == null) {
                        setText(null);setGraphic(null);
        
                } else {
                        if (mLLoader == null) {
                                mLLoader = new FXMLLoader(getClass().getResource("/ListCell/GroupListCell.fxml"));
                                mLLoader.setController(this);

                                try {
                                        mLLoader.load();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }

                        groupNameLabel.setText(group.getGroup().getName());
                        createdOnLabel.setText(group.getGroup().getCreatedOn());


                        setText(null);
                        setGraphic(anchorPane);
                }
        
        }
}
