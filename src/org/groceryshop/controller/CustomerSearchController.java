package org.groceryshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.groceryshop.model.Customer;

import java.util.List;

/**
 * Created by atul_saurabh on 1/11/16.
 */
public class CustomerSearchController {

    @FXML
    private TextField searchbox;
    @FXML
    private TableView customertable;
    @FXML
    private TableColumn customerid;
    @FXML
    private TableColumn customername;
    @FXML
    private TableColumn mobilenumber;
    @FXML
    private TableColumn address;
    @FXML
    private TableColumn details;

    private ObservableList<org.groceryshop.entity.Customer> customers = FXCollections.observableArrayList();


    public void initialize() {

        customerid.setCellValueFactory(new PropertyValueFactory<org.groceryshop.entity.Customer, String>("accountnumber"));
        customername.setCellValueFactory(new PropertyValueFactory<org.groceryshop.entity.Customer, String>("customername"));
        mobilenumber.setCellValueFactory(new PropertyValueFactory<org.groceryshop.entity.Customer, String>("mobilenumber"));
        address.setCellValueFactory(new PropertyValueFactory<org.groceryshop.entity.Customer, String>("address"));
        details.setCellValueFactory(new PropertyValueFactory<org.groceryshop.entity.Customer, Hyperlink>("details"));

        customertable.setItems(customers);
    }

    @FXML
    public void searchCustomer(ActionEvent event) {
        customers.clear();
        String nameToSearch = searchbox.getText();
        Customer customer = new Customer();
        List<org.groceryshop.entity.Customer> customerlist = customer.getAllCustomers(nameToSearch);
        for (org.groceryshop.entity.Customer c : customerlist) {
            Hyperlink hyperlink = new Hyperlink("Details");
            hyperlink.setGraphic(new ImageView(new Image("/org/groceryshop/image/details_1.png")));
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Customer id = " + c.getAccountnumber());
                }
            });
            c.setDetails(hyperlink);
        }
        customers.addAll(customerlist);
    }
}
