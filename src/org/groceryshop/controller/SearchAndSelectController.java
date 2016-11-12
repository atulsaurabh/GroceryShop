package org.groceryshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.groceryshop.entity.Customer;
import org.groceryshop.model.AddCustomer;

import java.util.List;

/**
 * Created by atul_saurabh on 2/11/16.
 */


public class SearchAndSelectController {
    private String nameToSearch;
    @FXML
    private TableView<Customer> searchandselectcustomertable;
    @FXML
    private TableColumn customername;
    @FXML
    private TableColumn customeraddress;
    @FXML
    private TableColumn customermobile;
    @FXML
    private TableColumn customerselect;
    private ItemController controller;
    private Stage childWindow;

    private ObservableList<Customer> data = FXCollections.observableArrayList();

    public SearchAndSelectController(String nameToSearch, ItemController controller, Stage stage) {
        this.nameToSearch = nameToSearch;
        this.controller = controller;
        this.childWindow = stage;

    }

    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        customername.setCellValueFactory(new PropertyValueFactory<Customer, String>("customername"));
        customeraddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        customermobile.setCellValueFactory(new PropertyValueFactory<Customer, String>("mobilenumber"));
        customerselect.setCellValueFactory(new PropertyValueFactory<Customer, RadioButton>("radioButton"));
        AddCustomer addCustomer = new AddCustomer();
        data.clear();
        List<Customer> customers = addCustomer.getAllCustomers(nameToSearch);
        for (Customer c : customers) {
            RadioButton r = new RadioButton();
            r.setToggleGroup(toggleGroup);
            r.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.callBack(c.getAccountnumber(), c.getCustomername());
                    childWindow.close();
                }
            });
            c.setRadioButton(r);
        }
        data.addAll(customers);
        searchandselectcustomertable.setItems(data);

    }

}
