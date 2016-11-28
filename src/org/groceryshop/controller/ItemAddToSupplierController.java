package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by bipul on 28/11/16.
 */
public class ItemAddToSupplierController {
    private SupplierController supplierController;
    @FXML
    private TextField itemname;


    public void addItemToSupplier(ActionEvent event) {
        supplierController.setItems(itemname.getText().toUpperCase());
        itemname.clear();
    }

    public void setSupplierController(SupplierController supplierController) {
        this.supplierController = supplierController;
    }
}
