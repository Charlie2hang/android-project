package com.example.myapplication.Club.RoutePlanning;


import java.io.Serializable;

public class RoutePlanning implements Serializable {



    private String distance;
    private String elevation;

    private String landmarks, accountName, location;

    public RoutePlanning() {
    }

    public RoutePlanning(String distance, String elevation, String landmarks, String accountName, String location) {
        this.distance = distance;
        this.elevation = elevation;
        this.landmarks = landmarks;
        this.accountName = accountName;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(String landmarks) {
        this.landmarks = landmarks;
    }
}
