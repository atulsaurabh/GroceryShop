package org.groceryshop.controller;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.groceryshop.entity.Supplier;
import org.groceryshop.model.SupplierModel;

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
    private GoogleMapView googleMapView;

    @FXML
    private TextField companyname;

    private GoogleMap googleMap;

    private GeocodingService service;
    private LatLong lati_longi;
    private StringProperty addressProperty = new SimpleStringProperty();

    public void initialize() {

        googleMapView.addMapInializedListener(this);
        addressProperty.bind(supplieraddress.textProperty());

    }


    public void createSupplier(ActionEvent event) {
        Supplier supplier = new Supplier();
        supplier.setSuppliername(suppliername.getText());
        supplier.setMobilenumber(mobilenumber.getText());
        supplier.setSupplieraddress(supplieraddress.getText());
        supplier.setCompanyname(companyname.getText());
        SupplierModel model = new SupplierModel();
        if (model.createSupplier(supplier)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sipplier added");
            alert.setContentText("Supplier Added Successfully");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail");
            alert.setContentText("Supplier Is Already Available");
            alert.showAndWait();
        }
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
