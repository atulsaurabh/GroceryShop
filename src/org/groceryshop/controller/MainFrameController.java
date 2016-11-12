package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bipul.saurabh on 10/26/16.
 */
public class MainFrameController {
    private Stage window;
    @FXML
    private Pane holder;
    private TabPane tabPane = new TabPane();
    private SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    private KeyCombination ctrl_t = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
    private Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();


    public void setWindow(Stage window) {
        this.window = window;
    }

    public void initialize() {


        Tab defaultTab = createSellItemTab();
        defaultTab.setClosable(false);
        tabPane.getTabs().add(defaultTab);
        holder.getChildren().add(tabPane);
        holder.setPrefWidth(primaryScreenBound.getWidth() - 80);
    }

    public void addItem(ActionEvent actionEvent) {
        /*TableView tableView = new TableView();
        TableColumn serialNumber = new TableColumn("Sr. No.");
        TableColumn itemName = new TableColumn("Item Name");
        TableColumn itemPrice = new TableColumn("Price");
        TableColumn unit = new TableColumn("Per Unit");
        serialNumber.prefWidthProperty().bind(tableView.widthProperty().multiply(0.10));
        itemName.prefWidthProperty().bind(tableView.widthProperty().multiply(0.4));
        itemPrice.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        unit.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        serialNumber.setResizable(false);

        tableView.setEditable(true);
        tableView.getColumns().addAll(serialNumber,itemName,itemPrice,unit);
        ScrollPane pane=(ScrollPane) window.getScene().lookup("#scrollpane");*/
        /*tableView.setMaxHeight(pane.getHeight());
        tableView.setMaxWidth(pane.getWidth());
        tableView.setPrefWidth(pane.getPrefWidth());
        tableView.setPrefHeight(pane.getPrefHeight());
        tableView.setPrefWidth(pane.getWidth()-2);
        tableView.setPrefHeight(pane.getHeight()-20);

        pane.setContent(tableView);*/

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/additem.fxml"));
            ScrollPane scrollpane = loader.load();
            scrollpane.setPrefWidth(window.getWidth() - 50);
            AnchorPane anchorPane = (AnchorPane) scrollpane.getContent().lookup("#anchor");
            anchorPane.setPrefWidth(window.getWidth() - 70);
            TableView tableView = (TableView) scrollpane.getContent().lookup("#datatable");
            tableView.setPrefWidth(window.getWidth() - 80);
            System.out.print(tableView.getId());
            Tab tab = new Tab("Add Item");
            tab.setContent(scrollpane);
            tabPane.getTabs().add(tab);
            selectionModel.select(tab);
           /* Pane pane = (Pane)window.getScene().lookup("#holder");
            pane.getChildren().addAll(scrollpane);*/
        } catch (IOException ioexception) {
            Logger.getLogger(MainFrameController.class.getName()).log(
                    Level.SEVERE, "unable to load view additem.fxml",
                    ioexception
            );
        }

    }

    public void createAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/addcustomer.fxml"));
            Pane pane = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setResizable(false);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ioexception) {
            Logger.getLogger(MainFrameController.class.getName()).log(
                    Level.SEVERE, "unable to load view addcustomer.fxml",
                    ioexception
            );
        }
    }


    public void searchCustomer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/searchcustomer.fxml"));
            ScrollPane pane = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Search Customer");
            stage.setResizable(false);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ioexception) {
            Logger.getLogger(MainFrameController.class.getName()).log(
                    Level.SEVERE, "unable to load view addcustomer.fxml",
                    ioexception
            );
        }
    }


    public void createNewTab(KeyEvent keyEvent) {
        if (ctrl_t.match(keyEvent)) {
            Tab sellItemTab = createSellItemTab();
            tabPane.getTabs().add(sellItemTab);
            selectionModel.select(sellItemTab);
        }
    }


    private Tab createSellItemTab() {
        Tab defaultTab = new Tab("sell item");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/sellitem.fxml"));
        try {
            AnchorPane pane = loader.load();
            pane.setPrefWidth(primaryScreenBound.getWidth() - 100);
            ScrollPane scrollPane = (ScrollPane) pane.lookup("#sellscroll");
            scrollPane.setPrefWidth(primaryScreenBound.getWidth() - 80);
            TableView table = (TableView) scrollPane.getContent().lookup("#selltable");
            table.setPrefWidth(primaryScreenBound.getWidth() - 100);
            defaultTab.setContent(pane);
        } catch (IOException ioexception) {
            Logger.getLogger(MainFrameController.class.getName()).log(
                    Level.SEVERE, "unable to load view sellitem.fxml",
                    ioexception
            );
        }
        return defaultTab;
    }


    public void openCreateGroupWindow(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/groups.fxml"));
            Pane pane = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Group");
            stage.setResizable(false);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ioexception) {
            Logger.getLogger(MainFrameController.class.getName()).log(
                    Level.SEVERE, "unable to load view groups.fxml",
                    ioexception
            );
        }
    }


    public void showUnitCreateForm(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/unit.fxml"));
            Pane pane = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Group");
            stage.setResizable(false);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ioexception) {
            Logger.getLogger(MainFrameController.class.getName()).log(
                    Level.SEVERE, "unable to load view unit.fxml",
                    ioexception
            );
        }
    }
}
