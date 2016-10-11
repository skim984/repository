package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.AccountType;

/**
 * Created by Sunpil Kim on 10/2/2016.
 */
public class ProfileScreenController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField titleField;

    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    private Stage _dialogStage;

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;
    }

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the student to be edited in the dialog.
     *
     * @param account the account that will be edited
     */
    public void setProfile(Account account) {

        //make the data show up in the gui fields
        emailField.setText(account.getProfile().getEmail());
        addressField.setText(account.getProfile().getAddress());
        titleField.setText(account.getProfile().getTitle());
    }


    @FXML
    private void handleSummitPressed() {
        this.getAccount().getProfile().setEmail(emailField.getText());
        this.getAccount().getProfile().setAddress(addressField.getText());
        this.getAccount().getProfile().setTitle(titleField.getText());
        _dialogStage.close();
    }

    @FXML
    private void handleCancelPressed() {
        _dialogStage.close();
    }



}
