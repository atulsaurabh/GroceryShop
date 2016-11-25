package org.groceryshop.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.groceryshop.entity.StoreItem;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class ItemNameCell extends TableCell<StoreItem, String> {
    private TextField item;

    private TableView<StoreItem> datatable;

    public ItemNameCell(TableView<StoreItem> datatable) {
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
    public void commitEdit(String newValue) {
        if (item.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Item Name Unavailable");
            alert.setContentText("Item Name Can Not Be Blank");
            alert.showAndWait();
            datatable.getSelectionModel().selectPrevious();
            return;
        }
        int k = datatable.getFocusModel().getFocusedIndex();
        StoreItem i = datatable.getItems().get(k);
        i.setItemname(newValue);
        setText(item.getText());
        setGraphic(null);
    }


    @Override
    protected void updateItem(String item, boolean empty) {
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
            setText(getItem());
            setGraphic(null);
        }
    }

    private void createItem() {
        item = new TextField();
        item.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        item.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue)
                    commitEdit(item.getText());
            }
        });
    }


}
