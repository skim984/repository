package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by nick on 10/11/16.
 */
public class Report {
    int id;
    String reporterName;
    DateFormat dateFormat;
    double locationLatitude;
    double locationLongitude;

    public Report(String name) {
        reporterName = name;
        Random rand = new Random();
        id = rand.nextInt(1000000) + 50000;
        dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    }

    public Report() {
        this("");
    }

    public String getReporterName() { return reporterName; }
    public String getDateReported() {
        Date date = new Date();
        return dateFormat.format(date);
    }
    public int getId() {
        return id;
    }

    public String getType() {
        return "None";
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
