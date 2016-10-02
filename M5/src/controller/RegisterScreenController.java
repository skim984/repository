package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.AccountType;
import model.Model;

import java.io.IOException;

/**
 * Created by Sunpil Kim on 10/1/2016.
 */
public class RegisterScreenController {
    @FXML
    private TextField registerIdField;
    @FXML
    private TextField registerPasswordField;
    @FXML
    private ComboBox<AccountType> registerTypefield = new ComboBox<>();

    /** the student whose data is being manipulated */
    private Account _account = new Account();

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        registerTypefield.getItems().setAll(FXCollections.observableArrayList(AccountType.values()));
        registerTypefield.setValue(AccountType.UR);
    }

//    /**
//     * Sets the student to be edited in the dialog.
//     *
//     * @param account the student who will be edited
//     */
//    public void setAccount(Account account) {
//        //remember the current student
//        _student = student;
//
//        if (_student == null) System.out.println("Student was null in addStudent!");
//
//        //make the data show up in the gui fields
//        nameField.setText(_student.getName());
//        majorField.setText(_student.getMajor());
//        standingField.setValue(ClassStanding.FR);
//    }

    @FXML
    private void handleOKPressed(ActionEvent event) throws IOException {
        if (isInputValid()) {
//            _account = new Account(registerIdField.getText(), registerPasswordField.getText(),
//                    registerTypefield.getSelectionModel().getSelectedItem());
            _account.setId(registerIdField.getText());
            _account.setPassword(registerPasswordField.getText());
            _account.setAccountType(registerTypefield.getSelectionModel().getSelectedItem().toString());
            if (!Model.getInstance().addAccount(_account)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Duplicated ID");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("This ID already exists!");
                alert.showAndWait();
            } else {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Login Page");
                stage.show();
            }
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (registerIdField.getText() == null || registerIdField.getText().length() == 0) {
            errorMessage += "No valid account name!\n";
        }
        if (registerPasswordField.getText() == null || registerPasswordField.getText().length() == 0) {
            errorMessage += "No valid password entered!\n";
        }


        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleCancelPressed(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }
}
