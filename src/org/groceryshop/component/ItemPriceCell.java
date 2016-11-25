package org.groceryshop.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import org.groceryshop.entity.StoreItem;

import java.util.Optional;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class ItemPriceCell extends TableCell<StoreItem, Float> {
    private TextField item;

    private TableView<StoreItem> datatable;
    private String pricefor;

    public ItemPriceCell(TableView<StoreItem> datatable, String pricefor) {

        this.datatable = datatable;
        this.pricefor = pricefor;
    }

    @Override
    public void startEdit() {

        if (!isEmpty()) {
            super.startEdit();
            createItem();
            setText(null);
            setGraphic(item);
            item.selectAll();
            item.requestFocus();
        }
    }

    @Override
    public void commitEdit(Float newValue) {
        int k = datatable.getFocusModel().getFocusedIndex();
        StoreItem i = datatable.getItems().get(k);
        switch (pricefor) {
            case "mrp":
                i.setMrp_priceperunit(newValue);
                break;
            case "sell":
                i.setSellingprice(newValue);
                break;
        }

        setText(item.getText());
        setGraphic(null);
    }


    @Override
    protected void updateItem(Float item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);

        } else if (isEditing()) {
            if (this.item != null)
                this.item.setText(null);
            setText(null);
            setGraphic(this.item);
        } else {
            setText(String.valueOf(getItem()));
            setGraphic(null);
        }
    }

    private void createItem() {
        item = new TextField();
        item.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        item.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    try {
                        float price = Float.parseFloat(item.getText());
                        if (price == 0) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirm");
                            alert.setContentText("Are you sure that price is 0");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK)
                                commitEdit(0.0f);
                            else
                                datatable.getSelectionModel().selectPrevious();
                        } else
                            commitEdit(price);
                    } catch (Exception e) {
                        if (item.getText().equals("")) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Invalid Price");
                            alert.setContentText("Price Can Not Be Blank");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Invalid Price");
                            alert.setContentText("Price Can be numeric only");
                            alert.showAndWait();
                        }
                        datatable.getSelectionModel().selectPrevious();
                    }

                }
            }
        });
    }
}
