package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Account;
import model.AccountType;
import model.Model;
import model.Report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 10/11/16.
 */
public class ViewReportController {
    private MainFXApplication mainApplication;
    private Account _account;
    private Stage _dialogStage;
    @FXML
    ListView<Report> reportListView;

    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;
    }

    public void setAccount(Account acct) {
        _account = acct;
    }

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    @FXML
    public void populateView() {
        List<Report> reportsList = FXCollections.observableArrayList(Model.getInstance().getReports());
        if(_account != null) {
            if (_account.getAccountType().equals(AccountType.UR.toString())) {
                List<Report> tempRep = new ArrayList<>();
                for (Report r : reportsList) {
                    if (!(r.getType().equals("Purity"))) {
                        tempRep.add(r);
                    }
                }
                reportListView = new ListView<>(FXCollections.observableArrayList(tempRep));
            } else {
                reportListView = new ListView<>(FXCollections.observableArrayList(reportsList));
            }
            reportListView.setEditable(false);
            StackPane root = new StackPane();
            root.getChildren().add(reportListView);
            _dialogStage.setScene(new Scene(root, 600, 300));
//            _dialogStage.show();
        }
    }

    @FXML
    private void handleCancelPressed() throws IOException {
        _dialogStage.close();
    }

}
