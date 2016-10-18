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
import model.Report;

import java.io.IOException;
import java.util.List;

/**
 * Created by nick on 10/11/16.
 */
public class PurityReportController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;
    public List<Report> reportsList;
    private Account account = new Account();
    private Stage _dialogStage;

    @FXML
    private TextField purityLocationText;

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
    public List<Report> setList(List<Report> reportsList) {
        this.reportsList = reportsList;
        return this.reportsList;
    }

    public List<Report> updateList() {
        return reportsList;
    }
    @FXML
    private void handleOKPressed() throws IOException {
        PurityReport pr = new PurityReport(account.getId());
        if (isInputValid()) {
            pr.setLoc(purityLocationText.getText());
            pr.setpCond(waterPurityComboBox.getValue());
            pr.setCPPM(Integer.parseInt(contamPPMText.getText()));
            pr.setVPPM(Integer.parseInt(virusPPMText.getText()));
            reportsList.add(pr);
            System.out.println(reportsList.toString());
            _dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errMess = "";

        if (purityLocationText.getText().isEmpty()) {
            errMess += "No valid location provided.\n";
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
            errMess += "One or more of the values provided as PPMs are invalid. Please ensure you have entered an integer.\n";
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
