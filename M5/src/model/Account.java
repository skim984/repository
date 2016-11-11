package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Sunpil Kim on 10/1/2016.
 */
public class Account {
    /** the account id*/
    private StringProperty _id = new SimpleStringProperty();

    /** the account password*/
    private StringProperty _password = new SimpleStringProperty();

    /** the account type for the account */
    private StringProperty _accountType = new SimpleStringProperty();

    /** the account type for the account */
    private Profile _profile = new Profile();

    public Profile getProfile() {
        return _profile;
    }

    /**
     * Makes a new account
     * @param id  the id of the account
     * @param password the password of the account
     * @param accountType the accountType for the account
     */
    public Account(String id, String password, String accountType) {
        _id.set(id);
        _password.set(password);
        _accountType.set(accountType);
    }

    public Account(){}

    public String getId() {
        return _id.get();
    }

    public void setId(String id) {
        _id.set(id);
    }

    public String getPassword() {
        return _password.get();
    }

    public void setPassword(String password) {
        _password.set(password);
    }

    public String getAccountType() {
        return _accountType.get();
    }

    public void setAccountType(String accountType) {
        _accountType.set(accountType);
    }

    /**
     *
     * @return the display string representation
     */
    public String toString() {
        return _id.get() + " " + _accountType.get();
    }

}
