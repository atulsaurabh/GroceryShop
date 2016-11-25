package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.groceryshop.entity.SellingUnitGroup;
import org.groceryshop.entity.UnitForSell;
import org.groceryshop.model.MeasurementGroupModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 10/11/16.
 */
public class UnitForSellController {
    @FXML
    private TextField unitname;
    @FXML
    private TextField divisionfactor;

    @FXML
    private ComboBox<String> measurementgroup;

    private String groupChoice;

    public void initialize() {
        String[] groupsName = new MeasurementGroupModel().getAllMeasurementGroup();
        measurementgroup.getItems().addAll(groupsName);

    }

    public void addUnit(ActionEvent actionEvent) {
        try {
            String measurementgroupname = measurementgroup.getSelectionModel().getSelectedItem();
            SellingUnitGroup grp = new MeasurementGroupModel().getSellingUnitByName(measurementgroupname);
            UnitForSell unitForSell = new UnitForSell();
            unitForSell.setGroup(grp);
            unitForSell.setUnitName(unitname.getText().toUpperCase());
            unitForSell.setDivisionFactor(Double.parseDouble(divisionfactor.getText()));
            grp.getSells().add(unitForSell);
            MeasurementGroupModel model = new MeasurementGroupModel();

            if (model.updateGroup(grp)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setContentText("Unit Added Successfully");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setContentText("Unit Not Added");
                alert.show();
            }

        } catch (Exception e) {
            Logger.getLogger(UnitForSellController.class.getName()).log(Level.SEVERE, "Unable to add unit for sell " + e.getMessage(), e);
        }
    }
}
