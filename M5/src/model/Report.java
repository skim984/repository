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


    public Report(String name) {
        reporterName = name;
        Random rand = new Random();
        id = rand.nextInt(1000000) + 50000;
        DateFormat toFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat = toFormat.format(date);
    }

    public Report() {
        this("");
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public String getReporterName() { return reporterName; }
    public String getDateReported() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getId() {
        return id;
    }
    public void setID(int id) { this.id = id; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(double longitude, double latitude) {
        locationLatitude = latitude;
        locationLongitude = longitude;
    }

    public String getLocation() {
        return locationLatitude +", " + locationLongitude;
    }


    @Override
    public String toString() {
        return "\nReport ID: " + id + " submitted by : " + reporterName + " on " +  this.getDateReported();
    }
}
