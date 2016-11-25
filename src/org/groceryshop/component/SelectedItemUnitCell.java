package org.groceryshop.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import org.groceryshop.entity.StoreItem;

import java.util.ArrayList;

/**
 * Created by atul_saurabh on 20/11/16.
 */
public class SelectedItemUnitCell extends TableCell<StoreItem, String> {
    private ComboBox<String> item;

    private TableView<StoreItem> datatable;
    private ArrayList<String> itemids;

    public SelectedItemUnitCell(TableView<StoreItem> datatable, ArrayList<String> itemids) {
        this.datatable = datatable;
        this.itemids = itemids;
    }

    @Override
    public void startEdit() {

        if (!isEmpty()) {
            super.startEdit();
            createItem();
            setText(null);
            setGraphic(item);
            // item.selectAll();
            item.requestFocus();
        }
    }

    public void commitEdit(String newValue) {
        int k = datatable.getFocusModel().getFocusedIndex();
        StoreItem i = datatable.getItems().get(k);
        // i.setSellunit(item.getSelectionModel().getSelectedItem());
        setText(item.getSelectionModel().getSelectedItem());
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
                this.item.getItems().removeAll();
            setText(null);
            setGraphic(this.item);
        } else {
            setText(getItem());
            setGraphic(null);
        }
    }

    private void createItem() {
        item = new ComboBox<>();
        item.setEditable(false);
        int k = datatable.getFocusModel().getFocusedIndex();
        long ids = Long.parseLong(itemids.get(k));
        // String unit = new ItemModel().getSellUnitForItem(ids);
        // item.getItems().add(unit);
        item.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        item.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int k = datatable.getFocusModel().getFocusedIndex();
                StoreItem item = datatable.getItems().get(k);
                //item.setSellunit(newValue);

            }
        });
        item.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue)
                    commitEdit(item.getSelectionModel().getSelectedItem());
            }
        });
    }
}
