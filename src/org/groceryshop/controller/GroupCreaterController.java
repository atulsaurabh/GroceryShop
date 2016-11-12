package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.groceryshop.entity.ItemGroup;
import org.groceryshop.model.GroupCreatorModel;

/**
 * Created by atul_saurabh on 6/11/16.
 */
public class GroupCreaterController {
    @FXML
    private TextField groupname;

    public void createGroup(ActionEvent event) {
        GroupCreatorModel model = new GroupCreatorModel();
        if (!model.isGroupExist(groupname.getText().toUpperCase())) {
            ItemGroup group = new ItemGroup();
            group.setGroupname(groupname.getText().toUpperCase());
            if (model.addGroup(group)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Group Added Successfully");
                alert.setTitle("Success");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Unable to add group");
                alert.setTitle("Fail");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Group Already Available");
            alert.setTitle("Available");
            alert.show();
        }

        groupname.clear();
    }
}
