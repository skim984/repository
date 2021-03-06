package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunpil Kim on 10/1/2016.
 */
public class Model {
    /** Set Model up as a singleton design pattern */
    private static final Model instance = new Model();

    /** Interact with controllers */
    public static Model getInstance() { return instance; }

    /** a list of all the accounts in the system */
    private final ObservableList<Account> accounts = FXCollections.observableArrayList();


    private final ObservableList<Report> reportsList = FXCollections.observableArrayList();


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
                Account account = new Account(_id, _pwd, _accountType);
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

        try {
            Statement statement;
            statement = connection.createStatement();
            String sql;
            sql = "SELECT * FROM Report NATURAL JOIN PurityReport";
            ResultSet rs = statement.executeQuery(sql);

            //Extract data from result set
            while (rs.next()) {
                //retrieve by column name
                int _reportId = Integer.parseInt(rs.getString("ReportId"));
                String _reporterName = rs.getString("ReporterName");
                String _dateFormat = rs.getTimestamp("DateFormat").toString().substring(0,19);
                double _latitude = Double.parseDouble(rs.getString("Latitude"));
                double _longitude = Double.parseDouble(rs.getString("Longitude"));
                int _VPPM = Integer.parseInt(rs.getString("VPPM"));
                int _CPPM = Integer.parseInt(rs.getString("CPPM"));
                String _purityCondition = rs.getString("PurityCondition");


                PurityReport report = new PurityReport(_reporterName);
                report.setID(_reportId);
                report.setDateFormat(_dateFormat);
                report.setLocation(_longitude, _latitude);
                report.setVPPM(_VPPM);
                report.setCPPM(_CPPM);
                report.setpCond(PurityCondition.valueOf(_purityCondition));

                reportsList.add(report);
            }

            sql = "SELECT * FROM Report NATURAL JOIN SourceReport";
            rs = statement.executeQuery(sql);

            //Extract data from result set
            while (rs.next()) {
                //retrieve by column name
                int _reportId = Integer.parseInt(rs.getString("ReportId"));
                String _reporterName = rs.getString("ReporterName");
                String _dateFormat = rs.getTimestamp("DateFormat").toString().substring(0,19);
                double _latitude = Double.parseDouble(rs.getString("Latitude"));
                double _longitude = Double.parseDouble(rs.getString("Longitude"));
                String _sourceType = rs.getString("SourceType");
                String _sourceCondition = rs.getString("SourceCondition");

                SourceReport report = new SourceReport(_reporterName);
                report.setID(_reportId);
                report.setDateFormat(_dateFormat);
                report.setLocation(_longitude, _latitude);
                report.setSt(SourceType.valueOf(_sourceType));
                report.setSc(SourceCondition.valueOf(_sourceCondition));

                reportsList.add(report);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Add a purity report in report array and DB
     * @param report report that will be put in array and DB
     */
    public void addPurityReport(PurityReport report) {
        for (Report r : reportsList) {
            if (r.getId() == report.getId()) {
                report.setID(report.getId() + 1);
            }
        }

        reportsList.add(report);

        // Add a query in DB
        try {
            PreparedStatement preparedStatement;
            // Insert an account in DB
            String insertTableSQL = "INSERT INTO Report"
                    + "(ReportId, ReporterName, DateFormat, Latitude, Longitude) VALUES"
                    + "(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, Integer.toString(report.getId()));
            preparedStatement.setString(2, report.getReporterName());
            preparedStatement.setString(3, report.getDateReported());
            preparedStatement.setString(4, Double.toString(report.getLocationLatitude()));
            preparedStatement.setString(5, Double.toString(report.getLocationLongitude()));

            //execute insert SQL Statement
            preparedStatement.executeUpdate();

            // Insert Profile in BD
            insertTableSQL = "INSERT INTO PurityReport"
                    + "(ReportId, VPPM, CPPM, PurityCondition) VALUES"
                    + "(?,?,?,?)";

            preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, Integer.toString(report.getId()));
            preparedStatement.setString(2, Integer.toString(report.getVPPM()));
            preparedStatement.setString(3, Integer.toString(report.getCPPM()));
            if (report.getpCond() != null) {
                preparedStatement.setString(4, report.getpCond().getCode());
            }


            //execute insert SQL Statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add a source report in report array and DB
     * @param report report that will be put in array and DB
     */
    public void addSourceReport(SourceReport report) {
        for (Report r : reportsList) {
            if (r.getId() == report.getId()) {
                report.setID(report.getId() + 1);
            }
        }

        reportsList.add(report);

        // Add a query in DB
        try {
            PreparedStatement preparedStatement;
            // Insert an account in DB
            String insertTableSQL = "INSERT INTO Report"
                    + "(ReportId, ReporterName, DateFormat, Latitude, Longitude) VALUES"
                    + "(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, Integer.toString(report.getId()));
            preparedStatement.setString(2, report.getReporterName());
            preparedStatement.setString(3, report.getDateReported());
            preparedStatement.setString(4, Double.toString(report.getLocationLatitude()));
            preparedStatement.setString(5, Double.toString(report.getLocationLongitude()));

            //execute insert SQL Statement
            preparedStatement.executeUpdate();

            // Insert Profile in BD
            insertTableSQL = "INSERT INTO SourceReport"
                    + "(ReportId, SourceType, SourceCondition) VALUES"
                    + "(?,?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, Integer.toString(report.getId()));
            if (report.getSc() != null && report.getSt() != null) {
                preparedStatement.setString(2, report.getSt().getCode());
                preparedStatement.setString(3, report.getSc().getCode());
            }


            //execute insert SQL Statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * called by controllers for initialize reports values in View
     * @return List that contains all reports.
     */
    public List<Report> getReports() {
        return reportsList;
    }

    private void connectJDBC() {
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

    public Report findReport(String reporterName, int id) {
        for (Report r : reportsList) {
            // check each reporter name/user id
            if (r.getReporterName().equals(reporterName)) {
                // check the id
                if (id != 0 && id == r.getId()) {
                    return r;
                }
            }
        }
        return null;
    }

    public void removeReport (Report r) {
        reportsList.remove(r);
    }

    public List<Report> findReportByUser(String reporterName) {
        if (reporterName == null){
            return null;
        }
        List<Report> tempList = new ArrayList<>();
        for (Report r : reportsList) {
            // check each reporter name/user id
            if (r.getReporterName().equals(reporterName)) {
                tempList.add(r);
            }
        }
        return tempList;
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
