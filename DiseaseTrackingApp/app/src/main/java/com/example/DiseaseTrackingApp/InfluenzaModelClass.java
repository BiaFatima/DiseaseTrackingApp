package com.example.DiseaseTrackingApp;

import com.google.gson.annotations.SerializedName;

public class InfluenzaModelClass {

    @SerializedName("week")
    private String week;

    @SerializedName("region")
    private String region;

    @SerializedName("cases")
    private String cases;

    @SerializedName("deaths")
    private String deaths;

    @SerializedName("recovered")
    private String recovered;

    @SerializedName("active")
    private String active;

    @SerializedName("today_cases")
    private String todayCases;

    @SerializedName("today_deaths")
    private String todayDeaths;

    @SerializedName("today_recovered")
    private String todayRecovered;

    @SerializedName("epiweek")
    private String epiweek;

    @SerializedName("total")
    private String total;

    // <-- ADD THIS
    @SerializedName("dateString")
    private String dateString;

    // Getters

    public String getWeek() { return week; }

    public String getRegion() { return region; }

    public String getCases() { return cases == null ? "0" : cases; }

    public String getDeaths() { return deaths == null ? "0" : deaths; }

    public String getRecovered() { return recovered == null ? "0" : recovered; }

    public String getActive() { return active == null ? "0" : active; }

    public String getTodayCases() { return todayCases == null ? "0" : todayCases; }

    public String getTodayDeaths() { return todayDeaths == null ? "0" : todayDeaths; }

    public String getTodayRecovered() { return todayRecovered == null ? "0" : todayRecovered; }

    public String getEpiweek() { return epiweek; }

    public void setEpiweek(String epiweek) { this.epiweek = epiweek; }

    public String getTotal() { return total; }

    // NEW getter for dateString
    public String getDateString() { return dateString; }
}
