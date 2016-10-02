package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Sunpil Kim on 10/2/2016.
 */
public class Profile {

    /** the profile email*/
    private StringProperty _email = new SimpleStringProperty();

    /** the profile address*/
    private StringProperty _address = new SimpleStringProperty();

    /** the title for the profile */
    private StringProperty _title = new SimpleStringProperty();

    public Profile() {
        _email.set("");
        _address.set("");
        _title.set("");
    }

    /**
     * Makes a new Course
     * @param email  the email of the account
     * @param address the address of the account
     * @param title the title for the account
     */
    public Profile(StringProperty email, StringProperty address, StringProperty title) {
        _email = email;
        _address = address;
        _title = title;
    }

    public String getEmail() {
        return _email.get();
    }

    public void setEmail(String email) {
        _email.set(email);
    }

    public String getAddress() {
        return _address.get();
    }

    public void setAddress(String address) {
        _address.set(address);
    }

    public String getTitle() {
        return _title.get();
    }

    public void setTitle(String title) {
        _title.set(title);
    }

    /**
     *
     * @return the display string representation
     */
    public String toString() {
        return _email.get() + " " + _address.get() + " " + _title.get();
    }

}
