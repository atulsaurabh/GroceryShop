package org.groceryshop.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.groceryshop.entity.StoreItem;

/**
 * Created by atul_saurabh on 6/11/16.
 */
public class ItemWeightCell extends TableCell<StoreItem, Double> {
    private TextField item;

    private TableView<StoreItem> datatable;

    public ItemWeightCell(TableView<StoreItem> datatable) {
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
    public void commitEdit(Double newValue) {
        int k = datatable.getFocusModel().getFocusedIndex();
        StoreItem i = datatable.getItems().get(k);
        i.setWeight(newValue);
        setText(item.getText());
        setGraphic(null);
    }


    @Override
    protected void updateItem(Double item, boolean empty) {
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
                        commitEdit(Double.parseDouble(item.getText()));
                    } catch (Exception e) {

                    }

                }
            }
        });
    }
}
