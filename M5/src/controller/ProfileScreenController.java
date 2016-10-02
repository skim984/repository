package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
//        this.setAccount(mainApplication.getCurrentAccount());
    }

    /**
     * Sets the student to be edited in the dialog.
     *
     * @param account the student who will be edited
     */
    public void setProfile(Account account) {
        //remember the current student
//        _account = student;
//
//        if (_student == null) System.out.println("Student was null in addStudent!");

        //make the data show up in the gui fields
        emailField.setText(account.getProfile().getEmail());
        addressField.setText(account.getProfile().getAddress());
        titleField.setText(account.getProfile().getTitle());
    }


    @FXML
    private void handleSummitPressed() {

    }

    @FXML
    private void handleCancelPressed() {

    }



}
