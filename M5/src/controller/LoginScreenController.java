package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


/**
 * Created by Sunpil Kim on 9/22/2016.
 */
public class LoginScreenController {
    @FXML
    private TextField loginUserField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML private javafx.scene.control.Button cancelLoginButton;

    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            // Load a new root layout from fxml file.
            Parent root = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Page");
            stage.show();
        }

    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (loginUserField.getText() == null || loginUserField.getText().length() == 0) {
            errorMessage += "No valid student name!\n";
        }
        if (loginPasswordField.getText() == null || loginPasswordField.getText().length() == 0) {
            errorMessage += "No valid major entered!\n";
        }
        if (!loginUserField.getText().equals("user") || !loginPasswordField.getText().equals("pass")) {
            errorMessage += "ID and Password are not matched!\n";
        }

        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    @FXML
    private void btnCancelAction() throws IOException {
        Stage stage = (Stage) cancelLoginButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        Stage welcomeStage = new Stage();
        Scene scene = new Scene(root);
        welcomeStage.setScene(scene);
        welcomeStage.setTitle("Login Page");
        welcomeStage.show();
    }

}
