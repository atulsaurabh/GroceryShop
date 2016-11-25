package org.groceryshop.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.groceryshop.entity.StoreItem;

/**
 * Created by atul_saurabh on 2/11/16.
 */
public class SellItemQuantityCell extends TableCell<StoreItem, Float> {
    private TextField item;

    private TableView<StoreItem> datatable;

    public SellItemQuantityCell(TableView<StoreItem> datatable) {
        this.datatable = datatable;
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
        i.setAvailablequantity(newValue);
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
                        commitEdit(Float.parseFloat(item.getText()));
                    } catch (NumberFormatException ne) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Kindly Enter a valid quantity\nEither it is blank or it is Non Number");
                        alert.setTitle("Invalid Quantity");
                        alert.show();
                    }

                }
            }
        });
    }
}
