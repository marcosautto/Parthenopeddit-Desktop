package it.marcosautto.parthenopeddit;

import it.marcosautto.parthenopeddit.model.GroupMember;
import it.marcosautto.parthenopeddit.util.DateParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import it.marcosautto.parthenopeddit.model.Group;

import java.io.IOException;
import java.text.ParseException;

public class GroupListViewController extends ListCell<GroupMember> {

        @FXML
        private Label groupNameLabel;

        @FXML
        private Label createdOnLabel;
        
        @FXML
        private AnchorPane anchorPane;

        private DateParser DateParser;

        private FXMLLoader mLLoader;
        
        @Override
        protected void updateItem(GroupMember group, boolean empty) {
                super.updateItem(group, empty);
                DateParser = new DateParser();

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
                        try {
                                createdOnLabel.setText(DateParser.parseDate(group.getGroup().getCreatedOn()));
                        } catch (ParseException e) {
                                e.printStackTrace();
                        }


                        setText(null);
                        setGraphic(anchorPane);
                }
        
        }
}
