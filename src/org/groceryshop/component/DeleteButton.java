package org.groceryshop.component;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.groceryshop.entity.StoreItem;

/**
 * Created by atul_saurabh on 31/10/16.
 */
public interface DeleteButton {
    static Button getDeleteButton(TableView datatable) {
        Button b = new Button("delete");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<StoreItem> selected, all;
                all = datatable.getItems();
                if (all.size() == 1)
                    return;
                selected = datatable.getSelectionModel().getSelectedItems();
                selected.forEach(all::remove);
            }
        });
        return b;
    }
}
