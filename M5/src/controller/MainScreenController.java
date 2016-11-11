package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Sunpil Kim on 9/22/2016.
 */
public class MainScreenController {

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

    @FXML
    private void btnCreateEditProfileAction() {

        mainApplication.showProfile();
    }

    @FXML
    private void btnLogoutAction(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout success!");
        alert.setHeaderText("Thank you for visiting!");
        alert.showAndWait();
        mainApplication.showWelcome();
    }

    @FXML
    private void btnPurityAction() {
        mainApplication.showSubmitPurity();
    }

    @FXML
    private void btnSourceAction() {
        mainApplication.showSubmitSource();
    }

    @FXML
    private void btnViewAction() { mainApplication.showViewScreen(); }

    @FXML
    private void btnMapAction()   { mainApplication.showMap(); }

    @FXML
    private void btnHistInitAction() { mainApplication.showHistIn(); }
}
