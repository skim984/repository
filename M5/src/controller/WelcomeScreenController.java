package controller;

import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Created by Sunpil Kim on 9/22/2016.
 */
public class WelcomeScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    /**
     * allow for calling back to the main application code if necessary
     * @param main the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException {
        mainApplication.showLogin();

    }

    @FXML
    private void btnRegisterAction(ActionEvent event) throws IOException {
        mainApplication.showRegister();


    }
}
