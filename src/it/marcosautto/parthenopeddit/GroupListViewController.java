package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Group;

import java.io.IOException;

public class GroupListViewController extends ListCell<Group> {

        @FXML
        private Label groupNameLabel;

        @FXML
        private Label createdOnLabel;
        
        @FXML
        private AnchorPane anchorPane;

        private FXMLLoader mLLoader;
        
        @Override
        protected void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);
        
                if(empty || group == null) {
                        setText(null);setGraphic(null);
        
                } else {
                        if (mLLoader == null) {
                                mLLoader = new FXMLLoader(getClass().getResource("fxml/ListCell/GroupListCell.fxml"));
                                mLLoader.setController(this);

                                try {
                                        mLLoader.load();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }

                        groupNameLabel.setText(group.getName());
                        createdOnLabel.setText(group.getCreatedOn());


                        setText(null);
                        setGraphic(anchorPane);
                }
        
        }
}
