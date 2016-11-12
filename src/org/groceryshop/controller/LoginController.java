package org.groceryshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atul_saurabh on 26/10/16.
 */
public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Button resetButton;

    private Stage parentStage;

    public void initialize() {
        loginButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/org/groceryshop/image/tick_20_20.png"))));
        resetButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/org/groceryshop/image/broom_20_20.png"))));
    }

    @FXML
    public void checkLogin(ActionEvent event) {
        if (username.getText().equals("atul") && userPassword.getText().equals("123")) {
            parentStage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/mainframe.fxml"));
                AnchorPane pane = loader.load();
                Stage mainframe = new Stage();
                MainFrameController controller = loader.getController();
                controller.setWindow(mainframe);
                Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
                Scene scene = new Scene(pane);
                mainframe.setScene(scene);
                mainframe.setX(primaryScreenBound.getMinX());
                mainframe.setY(primaryScreenBound.getMinY());
                mainframe.setWidth(primaryScreenBound.getWidth());
                mainframe.setHeight(primaryScreenBound.getHeight());
                mainframe.setTitle("Grocery Shop");
                mainframe.show();

            } catch (IOException ioexception) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,
                        "Unable to load view mainframe.fxml",
                        ioexception
                );
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid User Name or Password");
            alert.setTitle("Authentication Fails");
            alert.show();
            username.setText("");
            userPassword.setText("");
        }
    }

    @FXML
    public void resetForm(ActionEvent event) {
        username.setText("");
        userPassword.setText("");
    }

    @FXML
    public void promptResetPassword(ActionEvent event) {

    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }
}
