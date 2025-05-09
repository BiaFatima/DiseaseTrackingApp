package com.example.DiseaseTrackingApp;

public class CovidModelClass {
    String cases, todayCases, deaths, recovered, active, todayRecovered, country;

    public CovidModelClass(String country, String todayRecovered, String active, String recovered, String deaths, String todayCases, String cases) {
        this.country = country;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.recovered = recovered;
        this.deaths = deaths;
        this.todayCases = todayCases;
        this.cases = cases;
    }

    public String getCountry() {
        return country;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getCases() {
        return cases;
    }
}

