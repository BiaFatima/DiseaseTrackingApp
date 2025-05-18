package com.example.DiseaseTrackingApp.Models;

import com.google.gson.annotations.SerializedName;

public class InfluenzaRecord {

    @SerializedName("id")
    private int id;

    @SerializedName("country")
    private String country;

    @SerializedName("date")
    private String date;

    @SerializedName("cases")
    private int cases;

    // Constructor
    public InfluenzaRecord(int id, String country, String date, int cases) {
        this.id = id;
        this.country = country;
        this.date = date;
        this.cases = cases;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public int getCases() {
        return cases;
    }
}