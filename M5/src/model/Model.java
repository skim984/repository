package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Sunpil Kim on 10/1/2016.
 */
public class Model {
    /** Set Model up as a singleton design pattern */
    private static final Model instance = new Model();
    public static Model getInstance() { return instance; }

    /** a list of all the accounts in the system */
    private final ObservableList<Account> accounts = FXCollections.observableArrayList();

    /** */
    private Connection connection = null;

    /**
     * Make a new Model
     * Fill it with some canned classes and user for data.
     */
    private Model () {
        connectJDBC();
        try {
            Statement statement;
            statement = connection.createStatement();
            String sql;
            sql = "SELECT * FROM Account NATURAL JOIN Profile";
            ResultSet rs = statement.executeQuery(sql);

            //Extract data from result set
            while (rs.next()) {
                //retrieve by column name
                String _id = rs.getString("Id");
                String _pwd = rs.getString("Password");
                String _accountType = rs.getString("AccountType");
                String _email = rs.getString("Email");
                String _address = rs.getString("Address");
                String _title = rs.getString("Title");
                Account account = new Account();
                account.setId(_id);
                account.setPassword(_pwd);
                account.setAccountType(_accountType);
                account.getProfile().setEmail(_email);
                account.getProfile().setAddress(_address);
                account.getProfile().setTitle(_title);
                accounts.add(account);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        accounts.add(new Account("user", "pass", AccountType.AD));
//        accounts.get(0).getProfile().setEmail("ezweb28@gmail.com");
//        accounts.get(0).getProfile().setAddress("Georgia");
//        accounts.get(0).getProfile().setTitle("Student");
    }

    private boolean connectJDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MYSQL JDBC Driver?");
            e.printStackTrace();
        }

        System.out.println("MYSQL JDBC Driver Registered");

        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://" + "ec2-52-203-56-237.compute-1.amazonaws.com" + ":3306/cleanWater", "skim984", "1004");
//            connection = DriverManager.
//            getConnection("jdbc:mysql://ec2-54-197-199-129.compute-1.amazonaws.com:3306/" + "cleanWater" + "?user=" + "root" + "&password=" + "1004" + "&useUnicode=true&characterEncoding=UTF-8");
        } catch (SQLException e) {
            System.out.println("Connection Failed!:\n" + e.getMessage());
        }

        if (connection != null) {
            System.out.println("GOOD");
            return true;
        } else {
            return false;
        }
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

        try {
            Statement statement;
            statement = connection.createStatement();
            String sql;
            sql = "SELECT Id FROM Account WHERE Id='" + account.getId() + "'";
            ResultSet rs = statement.executeQuery(sql);

            //Extract data from result set
            while (rs.next()) {
                //retrieve by column name
                String _id = rs.getString("Id");
                if (_id != null) {
                    System.out.println("Id is exists!!");
                    return false;
                }
            }
            PreparedStatement preparedStatement;
            // Insert an account in DB
            String insertTableSQL = "INSERT INTO Account"
                    + "(Id, Password, AccountType) VALUES"
                    + "(?,?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, account.getId());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getAccountType());

            //execute insert SQL Statement
            preparedStatement.executeUpdate();

            // Insert Profile in BD
            insertTableSQL = "INSERT INTO Profile"
                    + "(Id, Email, Address, Title) VALUES"
                    + "(?,?,?, ?)";
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, account.getId());
            preparedStatement.setString(2, "");
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, "");

            //execute insert SQL Statement
            preparedStatement.executeUpdate();

            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//
//        //go through each account looking for duplicate id   O(n)
//        for (Account s : accounts) {
//            if (s.getId().equals(account.getId())) {
//                //oops found duplicate id, don't add and return failure signal
//                return false;
//            }
//        }
        //never found the id so safe to add it.
        accounts.add(account);

        //return the success signal
        return true;
    }

    public Account findAccount(String id, String password) {

        //go through each account looking for duplicate id   O(n)
        for (Account s : accounts) {
            //check id
            if (s.getId().equals(id)) {
                //check password
                if (s.getPassword().equals(password)) {
                    return s;
                }
            }
        }
        //never found the id
        //return the fail signal
        return null;
    }

    public void updateProfile(Account account) {
        try {

            PreparedStatement preparedStatement;
            // Insert an account in DB
            String updateTableSQL = "UPDATE Profile SET Email=?, Address= ?,"
                    + " Title= ? WHERE Id=?";
            preparedStatement = connection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, account.getProfile().getEmail());
            preparedStatement.setString(2, account.getProfile().getAddress());
            preparedStatement.setString(3, account.getProfile().getTitle());
            preparedStatement.setString(4, account.getId());

            //execute insert SQL Statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
