package org.groceryshop.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.groceryshop.controller.ItemController;
import org.groceryshop.entity.StoreItem;
import org.groceryshop.model.AddItemModel;

/**
 * Created by atul_saurabh on 2/11/16.
 */
public class SellItemNameCell extends TableCell<StoreItem, String> {
    private TextField item;
    private TableView<StoreItem> datatable;
    private ItemController controller;

    public SellItemNameCell(TableView<StoreItem> datatable, ItemController controller) {
        this.datatable = datatable;
        this.controller = controller;
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

        AutoCompletionBinding t = TextFields.bindAutoCompletion(item, t1 -> {
                    return new AddItemModel().getItems(t1.getUserText());
                }, new StringConverter<String>() {
                    @Override
                    public String toString(String object) {
                        String[] conv = object.split("@");
                        return conv[0];
                    }

                    @Override
                    public String fromString(String string) {
                        return string;
                    }
                }
        );
        t.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent event) {
                String[] conv = ((String) event.getCompletion()).split("@");
                int i = datatable.getFocusModel().getFocusedIndex();
                controller.setSelectedItem(conv[1], i);
            }
        });
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
