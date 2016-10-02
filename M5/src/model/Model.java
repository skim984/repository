package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Sunpil Kim on 10/1/2016.
 */
public class Model {
    /** Set Model up as a singleton design pattern */
    private static final Model instance = new Model();
    public static Model getInstance() { return instance; }

    /** a list of all the accounts in the system */
    private final ObservableList<Account> accounts = FXCollections.observableArrayList();

    /**
     * Make a new Model
     * Fill it with some canned classes and students for data.
     */
    private Model () {
        accounts.add(new Account("user", "pass", AccountType.AD));
    }

    /**
     *
     * @return  a list of all the account.  mostly used by UI to display in the table view
     */
    public ObservableList<Account> getAccounts() { return accounts; }

    /**
     * add a student to the current course
     *
     * @param account the account to add
     * @return true if student added, false if not added
     */
    public boolean addAccount(Account account) {
        if (account == null) {
            return false;
        }
        //go through each account looking for duplicate id   O(n)
        for (Account s : accounts) {
            if (s.getId().equals(account.getId())) {
                //oops found duplicate id, don't add and return failure signal
                return false;
            }
        }
        //never found the id so safe to add it.
        accounts.add(account);
        //return the success signal
        return true;
    }

    public boolean findAccount(String id, String password) {
        //go through each account looking for duplicate id   O(n)
        for (Account s : accounts) {
            //check id
            if (s.getId().equals(id)) {
                //check password
                if (s.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        //never found the id
        //return the fail signal
        return false;
    }

}
