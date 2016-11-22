package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.groceryshop.entity.UnitForSell;
import org.groceryshop.model.ItemModel;

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


    public void addUnit(ActionEvent actionEvent) {
        try {
            UnitForSell unitForSell = new UnitForSell();
            unitForSell.setUnitName(unitname.getText().toUpperCase());
            unitForSell.setDivisionFactor(Double.parseDouble(divisionfactor.getText()));
            ItemModel itemModel = new ItemModel();

            if (itemModel.addGroup(unitForSell)) {
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
