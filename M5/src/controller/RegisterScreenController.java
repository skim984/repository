package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.AccountType;
import model.Model;

import java.io.IOException;

/**
 * Created by Sunpil Kim on 10/1/2016.
 */
public class RegisterScreenController {
    @FXML
    private TextField registerIdField;
    @FXML
    private TextField registerPasswordField;
    @FXML
    private ComboBox<AccountType> registerTypefield = new ComboBox<>();

    private Stage _dialogStage;

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /** the student whose data is being manipulated */
    private Account _account = new Account();

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        registerTypefield.getItems().setAll(FXCollections.observableArrayList(AccountType.values()));
        registerTypefield.setValue(AccountType.UR);
    }

    @FXML
    private void handleOKPressed(ActionEvent event) throws IOException {
        if (isInputValid()) {
            _account.setId(registerIdField.getText());
            _account.setPassword(registerPasswordField.getText());
            _account.setAccountType(registerTypefield.getSelectionModel().getSelectedItem().toString());
            if (!Model.getInstance().addAccount(_account)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Duplicated ID");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("This ID already exists!");
                alert.showAndWait();
            } else {
                _dialogStage.close();
            }
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
        if (registerIdField.getText() == null || registerIdField.getText().length() == 0) {
            errorMessage += "No valid account name!\n";
        }
        if (registerPasswordField.getText() == null || registerPasswordField.getText().length() == 0) {
            errorMessage += "No valid password entered!\n";
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
    private void handleCancelPressed(ActionEvent event) throws IOException {
        _dialogStage.close();
    }
}
