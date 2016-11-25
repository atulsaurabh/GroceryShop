package org.groceryshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.groceryshop.component.DeleteButton;
import org.groceryshop.component.SelectedItemUnitCell;
import org.groceryshop.component.SellItemNameCell;
import org.groceryshop.component.SellItemQuantityCell;
import org.groceryshop.entity.StoreItem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 1/11/16.
 */
public class SellItemController implements ItemController {
    @FXML
    private TextField customername;
    @FXML
    private DatePicker selldatepicker;
    @FXML
    private TextField totalprice;
    @FXML
    private TableView sellitemtable;
    @FXML
    private TableColumn serialnumber;
    @FXML
    private TableColumn itemname;
    @FXML
    private TableColumn quantity;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn action;

    @FXML
    private TableColumn sellunit;

    private ObservableList<StoreItem> data = FXCollections.observableArrayList();

    private ArrayList<String> itemids = new ArrayList<>();

    private String customerid;

    private SellItemController me = this;

    private int srno = 0;

    public void initialize() {
        sellitemtable.getSelectionModel().setCellSelectionEnabled(true);
        sellitemtable.setEditable(true);
        sellitemtable.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    sellitemtable.getSelectionModel().selectNext();
                    TablePosition pos = sellitemtable.getFocusModel().getFocusedCell();
                    sellitemtable.edit(pos.getRow(), pos.getTableColumn());
                    if (pos.getColumn() == 5) {
                        StoreItem i = new StoreItem();
                        i.setAction(DeleteButton.getDeleteButton(sellitemtable));
                        data.add(i);
                    }
                    event.consume();

                }
            }
        });
        serialnumber.setCellValueFactory(new PropertyValueFactory<StoreItem, Integer>("srno"));
        itemname.setCellValueFactory(new PropertyValueFactory<StoreItem, String>("itemname"));
        quantity.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("price"));
        action.setCellValueFactory(new PropertyValueFactory<StoreItem, Button>("action"));
        sellunit.setCellValueFactory(new PropertyValueFactory<StoreItem, String>("sellunit"));
        itemname.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new SellItemNameCell(sellitemtable, me);
            }
        });
        quantity.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new SellItemQuantityCell(sellitemtable);
            }
        });
        sellunit.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new SelectedItemUnitCell(sellitemtable, itemids);
            }
        });
        StoreItem i = new StoreItem();
        i.setAction(DeleteButton.getDeleteButton(sellitemtable));
        data.add(i);
        sellitemtable.setItems(data);
        selldatepicker.setValue(LocalDate.now());

    }

    public void searchCustomer(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/searchandselectcustomer.fxml"));
        try {
            Stage searchandselectstage = new Stage();
            fxmlLoader.setController(new SearchAndSelectController(customername.getText(), this, searchandselectstage));
            ScrollPane pane = fxmlLoader.load();
            Scene searchandselectscene = new Scene(pane);
            searchandselectstage.setScene(searchandselectscene);
            searchandselectstage.setResizable(false);
            searchandselectstage.show();
        } catch (IOException ioexception) {
            Logger.getLogger(SellItemController.class.getName()).log(Level.SEVERE,
                    "Unable to load view searchandselectcustomer.fxml",
                    ioexception
            );
        }
    }

    @Override
    public void callBack(String customerid, String customerName) {
        this.customerid = customerid;
        customername.setText(customerName);
    }


    public void generateBill(ActionEvent actionEvent) {
        System.out.println("Customer id=" + customerid);
    }

    @Override
    public void setSelectedItem(String itemid, int index) {
        itemids.add(index, itemid);
    }
}
