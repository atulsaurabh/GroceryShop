package org.groceryshop.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.groceryshop.entity.StoreItem;
import org.groceryshop.entity.UnitForSell;
import org.groceryshop.model.ItemModel;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public class ItemUnitCell extends TableCell<StoreItem, String> {
    private TextField item;

    private TableView<StoreItem> datatable;
    private String fieldName;

    public ItemUnitCell(TableView<StoreItem> datatable, String fieldName) {
        this.datatable = datatable;
        this.fieldName = fieldName;

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
        int k = datatable.getFocusModel().getFocusedIndex();
        StoreItem i = datatable.getItems().get(k);
        switch (fieldName) {
            case "unit":
                //i.get
                break;
            case "itemunit":
                i.setPrintedunit(newValue);
        }

        setText(item.getText());
        setGraphic(null);
        //
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
        AutoCompletionBinding t = TextFields.bindAutoCompletion(item, t1 -> {
            return new ItemModel().getAllUnit(item.getText().toUpperCase());
        }, new StringConverter<UnitForSell>() {
            @Override
            public String toString(UnitForSell object) {
                return object.getUnitName();
            }

            @Override
            public UnitForSell fromString(String string) {
                UnitForSell u = new UnitForSell();
                u.setUnitName(string);
                u.setDivisionFactor(1);
                return u;
            }
        });
        item.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        item.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (fieldName.equals("unit") && item.getText().equals("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Selling unit Error");
                        alert.setContentText("Selling unit can not be blank");
                        alert.showAndWait();
                        datatable.getSelectionModel().selectPrevious();
                    } else
                    commitEdit(item.getText());
                }

            }
        });
    }
}
