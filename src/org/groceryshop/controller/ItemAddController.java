package org.groceryshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import org.groceryshop.component.*;
import org.groceryshop.entity.StoreItem;
import org.groceryshop.model.ItemModel;

import java.util.ArrayList;

/**
 * Created by bipul.saurabh on 10/27/16.
 */
public class ItemAddController implements ItemController {

    private static int TAB_COUNT = 0;
    @FXML
    private TableView datatable;
    @FXML
    private TableColumn sno;
    @FXML
    private TableColumn iname;
    @FXML
    private TableColumn iprice;
    @FXML
    private TableColumn iunit;
    @FXML
    private TableColumn action;
    @FXML
    private ProgressIndicator batchProgress;
    @FXML
    private TableColumn iavailable;
    @FXML
    private TableColumn iweight;
    @FXML
    private TableColumn igroup;

    @FXML
    private TableColumn itemUnit;

    @FXML
    private TableColumn isellprice;

    @FXML
    private TableColumn icost;

    private ObservableList<StoreItem> data = FXCollections.observableArrayList();

    private int srno = 0;
    private ItemAddController me = this;
    private ArrayList<Long> ids = new ArrayList<>();

    public void initialize() {
        datatable.getSelectionModel().setCellSelectionEnabled(true);
        datatable.setEditable(true);
        sno.setCellValueFactory(new PropertyValueFactory<StoreItem, Integer>("srno"));
        iname.setCellValueFactory(new PropertyValueFactory<StoreItem, String>("itemname"));
        iprice.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("mrp_priceperunit"));
        iunit.setCellValueFactory(new PropertyValueFactory<StoreItem, String>("sellunit"));
        action.setCellValueFactory(new PropertyValueFactory<StoreItem, Button>("action"));
        iweight.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("weightofitme"));
        iavailable.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("availablequantity"));
        igroup.setCellValueFactory(new PropertyValueFactory<StoreItem, String>("groupname"));
        itemUnit.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("printedunit"));
        isellprice.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("sellingprice"));
        icost.setCellValueFactory(new PropertyValueFactory<StoreItem, Float>("purchasepriceperunit"));
        iname.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemNameCell cell = new ItemNameCell(datatable);
                return cell;
            }
        });

        iprice.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemPriceCell cell = new ItemPriceCell(datatable, "mrp");
                return cell;
            }
        });

        isellprice.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemPriceCell cell = new ItemPriceCell(datatable, "sell");
                return cell;
            }
        });
        icost.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemPriceCell cell = new ItemPriceCell(datatable, "purchase");
                return cell;
            }
        });

        iunit.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemUnitCell cell = new ItemUnitCell(datatable, "unit");
                return cell;
            }
        });

        iweight.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemWeightCell cell = new ItemWeightCell(datatable);
                return cell;
            }
        });
        iavailable.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemAvailableCell cell = new ItemAvailableCell(datatable);
                return cell;
            }
        });

        igroup.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemGroupCell groupCell = new ItemGroupCell(datatable, me);
                return groupCell;
            }
        });

        itemUnit.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                ItemUnitCell cell = new ItemUnitCell(datatable, "itemunit");
                return cell;
            }
        });

        srno++;
        data.add(new StoreItem(srno, DeleteButton.getDeleteButton(datatable)));
        datatable.setItems(data);

        datatable.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    datatable.getSelectionModel().selectNext();
                    TablePosition pos = datatable.getFocusModel().getFocusedCell();
                    if (pos.getColumn() == 10) {
                        TAB_COUNT++;
                        if (TAB_COUNT == 2) {
                            StoreItem p = (StoreItem) datatable.getItems().get(pos.getRow());
                            datatable.getItems().add(new StoreItem(p.getSrno() + 1, DeleteButton.getDeleteButton(datatable)));
                            TAB_COUNT = 0;
                        }
                    }
                    datatable.edit(pos.getRow(), pos.getTableColumn());
                    event.consume();

                }
            }


        });
    }

    @FXML
    public void addItem(ActionEvent actionEvent) {
        ItemModel model = new ItemModel();
        if (model.addNewItem(data, batchProgress, ids)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Added Successfully");
            alert.setTitle("Success");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Problem Occurs in adding items");
            alert.setTitle("Fails");
            alert.show();
        }
    }

    @Override
    public void callBack(String customerid, String customerName) {
        return;
    }

    @Override
    public void setSelectedItem(String itemid, int index) {
        ids.add(index, Long.parseLong(itemid));
    }
}
