package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nick on 11/1/16.
 */
public class HistoryReportInController {
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");
    @FXML
    private TextField histSubmitLongText;
    @FXML
    private TextField histSubmitLatText;
    @FXML
    private ComboBox<String> PPMTypeComboBox;
    @FXML
    private TextField histSubmitYearText;
    private Stage _dialogStage;
    private Account account;
    private MainFXApplication mainApplication;
    private List<Report> reportsList;
    private Map<String, Integer> pureList;

    public void setAccount(Account a) {
        account = a;
    }

    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    public void setDialogStage(Stage ds) {
        _dialogStage = ds;
    }

    public void initialize() {
        PPMTypeComboBox.setOnMousePressed(event -> PPMTypeComboBox.requestFocus());
        PPMTypeComboBox.getItems().setAll("Virus", "Contaminant");
        PPMTypeComboBox.setValue("Virus");

    }

    public boolean isAllowed(Account acct) {
        if (!(acct.getAccountType().equals("MANAGER"))) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid User Level");
            a.setHeaderText("User Permissions Denied");
            a.setContentText("You don't have permission to " +
                    "submit a purity report. If you feel this is " +
                    "an error, please contact the site administrators.");
            a.getDialogPane().getChildren().stream()
                    .filter(node -> node instanceof Label)
                    .forEach(node ->
                            ((Label)node)
                                    .setMinHeight(Region.USE_PREF_SIZE));
            a.showAndWait();
            return false;
        }
        return true;
    }

    public boolean verifyInput() {
        String errMess = "";
        if (histSubmitLatText.getText().equals("")) {
            errMess += "Please enter a valid latitude.\n";
        }
        if (histSubmitLongText.getText().equals("")) {
            errMess += "Please enter a valid longitude.\n";
        }
        if (histSubmitYearText.getText().equals("")) {
            errMess += "Please enter a valid year.\n";
        }
        try {
            Double.parseDouble(histSubmitLongText.getText());
            Double.parseDouble(histSubmitLatText.getText());
            Integer.parseInt(histSubmitYearText.getText());
        } catch (NumberFormatException e) {
            errMess += "One or more of the values submitted " +
                    "are invalid. Please double-check your values, " +
                    "and resubmit.\nIf this issue persists, please contact " +
                    "site administrators.\n";
        }

        if (errMess.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMess);
            alert.getDialogPane().getChildren().stream()
                    .filter(node -> node instanceof Label)
                    .forEach(node ->
                            ((Label) node)
                                    .setMinHeight(Region.USE_PREF_SIZE));
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleCancelPressed() throws IOException {
        _dialogStage.close();
    }

    public void handleSubmitPressed() {
        if (verifyInput()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MainFXApplication.class.getResource("../view/HistoryReportOutputScreen.fxml"));
                AnchorPane newPage;
                newPage = fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setTitle("View Historical Reports");
                newStage.initModality(Modality.WINDOW_MODAL);
                Scene newScene = new Scene(newPage);
                HistoryReportViewController viewController = fxmlLoader.getController();
                viewController.initialize(getParams());
                viewController.setAccount(account);
                viewController.barPop();
                viewController.setMainFXApplication(mainApplication);
                newStage.setScene(newScene);
                newStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            _dialogStage.close();
        }
    }

    public String[] getParams() {
        return new String[]{histSubmitLatText.getText(), histSubmitLongText.getText(), histSubmitYearText.getText(), PPMTypeComboBox.getValue()};
    }
}
