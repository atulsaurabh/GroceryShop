package org.groceryshop.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.groceryshop.controller.LoginController;

/**
 * Created by atul_saurabh on 26/10/16.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/groceryshop/view/loginframe.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setParentStage(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login Please");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
