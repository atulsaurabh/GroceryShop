package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.groceryshop.entity.ItemGroup;
import org.groceryshop.entity.SellingUnitGroup;
import org.groceryshop.model.ItemGroupModel;
import org.groceryshop.model.ItemModel;

import java.util.List;

/**
 * Created by atul_saurabh on 6/11/16.
 */
public class GroupCreaterController {
    @FXML
    private TextField groupname;

    @FXML
    private ComboBox<String> itemgroupname;

    private String itemgroup;

    public void initialize() {
        List<SellingUnitGroup> groups = new ItemModel().getAllItemGroup();
        for (SellingUnitGroup g : groups)
            itemgroupname.getItems().add(g.getGroupname());

    }

    public void createGroup(ActionEvent event) {
        ItemGroupModel model = new ItemGroupModel();
        String g_name = itemgroupname.getSelectionModel().getSelectedItem();
        if (!model.isGroupExist(groupname.getText().toUpperCase())) {
            ItemGroup group = new ItemGroup();
            SellingUnitGroup sellingUnitGroup = model.getSellingUnitByName(g_name);
            group.setSellingUnitGroup(sellingUnitGroup);
            group.setGroupname(groupname.getText().toUpperCase());
            if (model.mergeGroup(group)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Group Added Successfully");
                alert.setTitle("Success");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Unable to add group");
                alert.setTitle("Fail");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Group Already Available");
            alert.setTitle("Available");
            alert.show();
        }

        groupname.clear();
    }
}
