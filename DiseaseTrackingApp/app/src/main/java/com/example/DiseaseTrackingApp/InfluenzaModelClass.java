package com.example.DiseaseTrackingApp;

public class InfluenzaModelClass {
    private String region;   // The region (country) name
    private String cases;    // Total cases
    private String deaths;   // Total deaths
    private String recovered; // Recovered cases
    private String active;   // Active cases
    private String todayCases; // Cases reported today
    private String todayRecovered; // Recoveries reported today

    // Constructor
    public InfluenzaModelClass(String region, String cases, String deaths, String recovered,
                               String active, String todayCases, String todayRecovered) {
        this.region = region;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.todayCases = todayCases;
        this.todayRecovered = todayRecovered;
    }

    // Getters
    public String getRegion() {
        return region;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }
}
