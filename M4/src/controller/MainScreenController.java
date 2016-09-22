package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Sunpil Kim on 9/22/2016.
 */
public class MainScreenController {
    @FXML
    private void btnLogoutAction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout success!");
        alert.setHeaderText("Thank you for visiting!");
        alert.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        // Show the scene containing the root layout.
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        // Set the Main App title
        stage.setTitle("Welcome!");
        stage.show();
    }
}
