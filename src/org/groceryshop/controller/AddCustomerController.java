package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.groceryshop.model.Customer;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class AddCustomerController {
    @FXML
    private Button customeradd;
    @FXML
    private TextField customername;
    @FXML
    private TextField occupation;
    @FXML
    private TextArea address;
    @FXML
    private TextField mobilenumber;
    @FXML
    private TextField accountnumber;

    public void initialize() {

        customeradd.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/org/groceryshop/image/plus_20_20.png"))));
        accountnumber.setText(Customer.getNextAccountNumber());
    }

    public void addCustomer(ActionEvent event) {
        org.groceryshop.entity.Customer customer = new org.groceryshop.entity.Customer();
        customer.setAccountnumber(accountnumber.getText());
        customer.setCustomername(customername.getText());
        customer.setOccupation(occupation.getText());
        customer.setAddress(address.getText());
        customer.setMobilenumber(mobilenumber.getText());
        Customer addCustomer = new Customer();
        if (addCustomer.addCustomer(customer)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Added Successfully");
            alert.setTitle("Success");
            alert.show();
            customername.clear();
            occupation.clear();
            address.clear();
            mobilenumber.clear();
            accountnumber.setText(Customer.getNextAccountNumber());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Customer Not Added ");
            alert.setTitle("Fail");
            alert.show();
        }
    }
}
