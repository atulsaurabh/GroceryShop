package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.groceryshop.entity.SellingUnitGroup;
import org.groceryshop.model.MeasurementGroupModel;

/**
 * Created by atul_saurabh on 22/11/16.
 */
public class MeasurementGroupController {
    @FXML
    private TextField groupname;

    public void createMeasurementGroup(ActionEvent event) {
        SellingUnitGroup sellingUnitGroup = new SellingUnitGroup();
        sellingUnitGroup.setGroupname(groupname.getText().toUpperCase());
        MeasurementGroupModel model = new MeasurementGroupModel();
        if (model.addGroup(sellingUnitGroup)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Measurement Group Added");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setContentText("Unable To add measurement group");
            alert.show();
        }
    }
}
