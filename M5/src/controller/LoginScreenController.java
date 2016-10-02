package controller;

import fxapp.MainFXApplication;
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
import model.Model;


import java.io.IOException;


/**
 * Created by Sunpil Kim on 9/22/2016.
 */
public class LoginScreenController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    @FXML
    private TextField loginUserField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML private javafx.scene.control.Button cancelLoginButton;

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;
    }

    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            mainApplication.showMain();
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
            errorMessage += "No valid User ID!\n";
        }
        if (loginPasswordField.getText() == null || loginPasswordField.getText().length() == 0) {
            errorMessage += "No valid Password entered!\n";
        }
        if (!Model.getInstance().findAccount(loginUserField.getText(), loginPasswordField.getText())) {
            errorMessage += "ID or Password are not matched!\n";
        }

        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApplication.getMainScreen());
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
        mainApplication.showWelcome();
    }

}
