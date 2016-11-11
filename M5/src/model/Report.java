package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by nick on 10/11/16.
 */
public class Report {
    private int id;
    private String reporterName;
    private String dateFormat;
    private double locationLatitude;
    private double locationLongitude;
    private String type;


    /**
     * Constructor method created when reports are made
     * @param name reporter's name
     */
    public Report(String name) {
        reporterName = name;
        Random rand = new Random();
        id = rand.nextInt(1000000) + 50000;
        DateFormat toFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat = toFormat.format(date);
    }

    /**
     * Empty report that is used for DB
     */
    public Report() {
        this("");
    }

    /**
     * getter
     * @return latitude value of double
     */
    public double getLocationLatitude() {
        return locationLatitude;
    }

    /**
     * getter
     * @return longitude value of double
     */
    public double getLocationLongitude() {
        return locationLongitude;
    }

    /**
     * getter
     * @return name of the reporter value of String
     */
    public String getReporterName() { return reporterName; }

    /**
     * getter
     * @return the date of reported value of String
     */
    public String getDateReported() {
        return dateFormat;
    }

    /**
     * setter
     * @param dateFormat will be set up by controller
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * getter
     * @return the report unique id value of integer
     */
    public int getId() {
        return id;
    }

    /**
     * setter
     * @param id set the incremental report id
     */
    public void setID(int id) { this.id = id; }

    /**
     * getter
     * @return the type of report value of String
     */
    public String getType() {
        return type;
    }

    /**
     * setter
     * @param type will be set up by controller
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * setter
     * @param longitude will be set up by controller
     * @param latitude will be set up by controller
     */
    public void setLocation(double longitude, double latitude) {
        locationLatitude = latitude;
        locationLongitude = longitude;
    }

    /**
     * getter
     * @return concatenated values of latitude and longitude
     */
    public String getLocation() {
        return locationLatitude +", " + locationLongitude;
    }

    /**
     * toString
     * @return concatenated values of all information including id, name, and date of being made
     */
    @Override
    public String toString() {
        return "\nReport ID: " + id + " submitted by : " + reporterName + " on " +  this.getDateReported();
    }
}
