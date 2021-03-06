package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.*;
import java.util.*;

/**
 * Created by nick on 11/1/16.
 */
public class HistoryReportViewController {
    private String type;
    private int year;
    private double latitude;
    private double longitude;

    @FXML
    LineChart histOutputLineGraph;
    @FXML
    CategoryAxis histOutputXAxis;
    @FXML
    NumberAxis histOutputYAxis;

    private MainFXApplication mainFXApplication;
    private Map<String, Integer> pureList;
    private Account account;
    private Stage _dialogStage;

    public void initialize(String[] args) {
        this.latitude = Double.parseDouble(args[0]);
        this.longitude = Double.parseDouble(args[1]);
        this.year = Integer.parseInt(args[2]);
        this.type = args[3];
        pureList = new HashMap<>();
    }

    public void barPop() {
        //defining the axes
        retrieveData();
        histOutputXAxis = new CategoryAxis();
        histOutputXAxis.setLabel("Months");
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = "" + (i + 1);
        }
        histOutputXAxis.setCategories(FXCollections.observableArrayList(months));

        histOutputYAxis = new NumberAxis();

        //creating the chart
        histOutputLineGraph = new LineChart<>(histOutputXAxis, histOutputYAxis);

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Data");
        //populating the series with data
        for (int i = 1; i < 13; i++) {
            series.getData().add(new XYChart.Data(i, pureList.get(i)));
        }
        histOutputLineGraph.getData().add(series);
    }

    private void retrieveData() {
        List<Report> reportList = FXCollections.observableArrayList(Model.getInstance().getReports());
        if (isAllowed()) {
            for (Report r : reportList) {
                if (isValidReport(r)) {
                    String month = "10";
                    if (type.equals("Contaminant")) {
                        if (!pureList.containsKey(month)) {
                            pureList.put(month, ((PurityReport) r).getCPPM());
                        } else {
                            int PPMavg = (int) ((pureList.get(month) + ((PurityReport) r).getCPPM()) * 0.5);
                            pureList.put(month, PPMavg);
                        }
                    } else {
                        if (!pureList.containsKey(month)) {

                            pureList.put(month, ((PurityReport) r).getCPPM());

                        } else {
                            int PPMavg = (int) ((pureList.get(month) + ((PurityReport) r).getVPPM()) * 0.5);
                            pureList.put(month, PPMavg);
                        }
                    }
                    System.out.println("Month: " + month);
                }
            }
        } else {
            throw new NullPointerException("User account is null! Something went wrong.");
        }
        System.out.println(pureList.values().toString());
        System.out.println(pureList.keySet().toString());
    }

    private boolean isValidReport(Report r) {
        if (r.getDateReported().contains("" + year)) {
            System.out.println("Purity test:" + r.getType().equals("Purity"));
            if (r.getType().equals("Purity")) {
                System.out.println("Location test: " + (r.getLocationLatitude() == latitude
                        && (r.getLocationLongitude() == longitude)));
                if ((r.getLocationLatitude() == latitude
                        && (r.getLocationLongitude() == longitude))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAllowed() {
        if (account != null) {
            if (account.getAccountType().equals(AccountType.UR.toString()) ||
                    account.getAccountType().equals(AccountType.AD.toString())) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Invalid User Level");
                a.setHeaderText("User Permissions Denied");
                a.setContentText("You don't have permission to " +
                        "view history reports. If you feel this is " +
                        "an error, please contact the site administrators.");
                a.getDialogPane().getChildren().stream()
                        .filter(node -> node instanceof Label)
                        .forEach(node ->
                                ((Label) node)
                                        .setMinHeight(Region.USE_PREF_SIZE));
                a.showAndWait();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }


    public void setMainFXApplication(MainFXApplication mainFXApplication) {
        this.mainFXApplication = mainFXApplication;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setDialogStage(Stage dialogStage) {
        this._dialogStage = dialogStage;
    }

    public void handleCancelPressed() {
        _dialogStage.close();
    }
}
