package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by nick on 10/11/16.
 */
public class SourceReportController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;
    private Account account;
    private Stage _dialogStage;


    @FXML
    ComboBox<SourceType> waterTypeBox;
    @FXML
    ComboBox<SourceCondition> waterConditionBox;
    @FXML
    TextField sourceLocationText;
    private List<Report> reportsList;

    @FXML
    public void initialize() {
        waterTypeBox.getItems().setAll(FXCollections.observableArrayList(SourceType.values()));
        waterTypeBox.setValue(SourceType.BT);

        waterConditionBox.getItems().setAll(FXCollections.observableArrayList(SourceCondition.values()));
        waterConditionBox.setValue(SourceCondition.WS);
    }

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;
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
        SourceReport rep = new SourceReport(account.getId());
        if(isInputValid()) {
            rep.setLoc(sourceLocationText.getText());
            rep.setSc(waterConditionBox.getValue());
            rep.setSt(waterTypeBox.getValue());
            reportsList.add(rep);
            _dialogStage.close();
        }

    }

    private boolean isInputValid() {
        String errMess = "";

        if (sourceLocationText.getText().isEmpty()) {
            errMess += "No valid location provided.";
        }

        if (errMess.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMess);
            return false;
        }
    }
}
