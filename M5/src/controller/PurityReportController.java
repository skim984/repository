package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.PurityCondition;
import model.PurityReport;

import java.io.IOException;

/**
 * Created by nick on 10/11/16.
 */
public class PurityReportController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;
    private Account account = new Account();
    private Stage _dialogStage;

    @FXML
    private TextField latitudeTextField;

    @FXML
    private TextField longitudeTextField;

    @FXML
    private TextField virusPPMText;

    @FXML
    private TextField contamPPMText;

    @FXML
    private ComboBox<PurityCondition> waterPurityComboBox;


    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;
    }

    @FXML
    public void initialize() {
        waterPurityComboBox.setOnMousePressed(event -> waterPurityComboBox.requestFocus());
        waterPurityComboBox.getItems().setAll(FXCollections.observableArrayList(PurityCondition.values()));
        waterPurityComboBox.setValue(PurityCondition.SF);
    }


    public void setAccount(Account acct) {
        account = acct;
    }

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    @FXML
    private void handleCancelPressed() throws IOException {
        _dialogStage.close();
    }

    @FXML
    private void handleOKPressed() throws IOException {
        PurityReport pr = new PurityReport(account.getId());
        if (isInputValid()) {
            pr.setLocation(Double.parseDouble(longitudeTextField.getText()),
                    Double.parseDouble(latitudeTextField.getText()));
            pr.setpCond(waterPurityComboBox.getValue());
            pr.setCPPM(Integer.parseInt(contamPPMText.getText()));
            pr.setVPPM(Integer.parseInt(virusPPMText.getText()));
            model.Model.getInstance().addPurityReport(pr);
            _dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errMess = "";

        if (longitudeTextField.getText().isEmpty()) {
            errMess += "No valid location longitude provided.\n";
        }
        if (latitudeTextField.getText().isEmpty()) {
            errMess += "No valid location latitude provided.\n";
        }
        if (contamPPMText.getText().isEmpty()) {
            errMess += "No valid Contaminant PPM provided.\n";
        }
        if (virusPPMText.getText().isEmpty()) {
            errMess += "No valid Virus PPM provided.\n";
        }

        try {
            Integer.parseInt(contamPPMText.getText());
            Integer.parseInt(virusPPMText.getText());
        } catch (NumberFormatException e) {
            errMess += "One or more of the values provided as " +
                    "PPMs are invalid. Please ensure you " +
                    "have entered an integer.\n";
        }

        try {
            Double.parseDouble(latitudeTextField.getText());
            Double.parseDouble(latitudeTextField.getText());
        } catch (NumberFormatException e) {
            errMess += "One or more of the values provided" +
                    " as locations are invalid. " +
                    "Please ensure you have entered a real number.\n";
        }

        if (errMess.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMess);
            alert.showAndWait();

            return false;
        }
    }
}

