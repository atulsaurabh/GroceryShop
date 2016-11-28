package org.groceryshop.controller;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import org.groceryshop.entity.StoreItem;
import org.groceryshop.model.ItemModel;

import java.util.List;

/**
 * Created by bipul on 26/11/16.
 */
public class SupplierController implements MapComponentInitializedListener {

    @FXML
    private TextField suppliername;
    @FXML
    private TextField mobilenumber;
    @FXML
    private TextField supplieraddress;
    @FXML
    private ListView<String> items;

    @FXML
    private GoogleMapView googleMapView;

    private GoogleMap googleMap;

    private GeocodingService service;
    private LatLong lati_longi;
    private StringProperty addressProperty = new SimpleStringProperty();

    public void initialize() {
        items.setEditable(true);
        items.setCellFactory(TextFieldListCell.forListView());
        List<StoreItem> storeItems = new ItemModel().getAllItems();

        ObservableList<String> lst = FXCollections.observableArrayList();
        for (StoreItem s : storeItems) {
            // System.out.println(s.getItemname());
            items.getItems().add(s.getItemname());
        }
        items.getItems().addAll("123");
        items.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        items.refresh();
        googleMapView.addMapInializedListener(this);
        addressProperty.bind(supplieraddress.textProperty());

    }


    public void createSupplier(ActionEvent event) {

    }


    public void openItemAddBox(ActionEvent event) {

    }


    @Override
    public void mapInitialized() {

        service = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(21.7679, 78.8718))
                .mapType(MapTypeIdEnum.ROADMAP)
                .panControl(true)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(11);
        googleMap = googleMapView.createMap(mapOptions);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLong(21.7, 78.8))
                .label(suppliername.getText())
                .visible(Boolean.TRUE);
        Marker marker = new Marker(markerOptions);
        googleMap.addMarker(marker);

    }

    public void findAddress(ActionEvent keyEvent) {

            service.geocode(addressProperty.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
                lati_longi = null;
                if (status == GeocoderStatus.ZERO_RESULTS) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("No Address found");
                    alert.showAndWait();
                    return;
                } else {
                    lati_longi = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(lati_longi)
                            .label(suppliername.getText())
                            .visible(Boolean.TRUE);
                    Marker marker = new Marker(markerOptions);
                    googleMap.addMarker(marker);
                    googleMap.setCenter(lati_longi);
                }
            });
        }
}
