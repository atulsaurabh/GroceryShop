package org.groceryshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.groceryshop.entity.ItemGroup;
import org.groceryshop.entity.SellingUnitGroup;
import org.groceryshop.entity.SubCatagory;
import org.groceryshop.model.ItemGroupModel;
import org.groceryshop.model.ItemModel;

import java.util.List;

/**
 * Created by atul_saurabh on 6/11/16.
 */
public class GroupCreaterController {
    ObservableList<SubCatagory> catagories = FXCollections.observableArrayList();
    @FXML
    private TextField groupname;
    @FXML
    private TextField subcatagory;
    @FXML
    private TableView subcatagorylist;
    @FXML
    private ComboBox<String> itemmeasurementgroupname;
    @FXML
    private TableColumn subcatagory_name;
    @FXML
    private TableColumn subcat_measure;

    private String itemgroup;

    public void initialize() {
        List<SellingUnitGroup> groups = new ItemModel().getAllItemGroup();
        for (SellingUnitGroup g : groups)
            itemmeasurementgroupname.getItems().add(g.getGroupname());

        subcatagory_name.setCellValueFactory(new PropertyValueFactory<SubCatagory, String>("subcatagoryname"));
        subcat_measure.setCellValueFactory(new PropertyValueFactory<SubCatagory, SellingUnitGroup>("sellingUnitGroup"));
        subcatagorylist.setItems(catagories);

    }

    public void createGroup(ActionEvent event) {
        ItemGroupModel model = new ItemGroupModel();
        String g_name = itemmeasurementgroupname.getSelectionModel().getSelectedItem();
        if (!model.isGroupExist(groupname.getText().toUpperCase())) {
            ItemGroup group = new ItemGroup();
            group.setGroupname(groupname.getText().toUpperCase());
            SellingUnitGroup sellingUnitGroup = model.getSellingUnitByName(g_name);
            //group.setSellingUnitGroup(sellingUnitGroup);
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

    public void addSubCatagory(ActionEvent event) {
        SellingUnitGroup g = new SellingUnitGroup();
        g.setGroupname(itemmeasurementgroupname.getSelectionModel().getSelectedItem());
        SubCatagory subCatagory = new SubCatagory();
        subCatagory.setSubcatagoryname(subcatagory.getText().toUpperCase());
        subCatagory.setSellingUnitGroup(g);
        catagories.add(subCatagory);
        subcatagory.clear();
    }
}
