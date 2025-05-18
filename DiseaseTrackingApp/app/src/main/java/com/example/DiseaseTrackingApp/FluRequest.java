package com.example.DiseaseTrackingApp;

import java.util.List;

public class FluRequest {
    private List<String> regions;
    private String epiweeks;

    public FluRequest(List<String> regions, String epiweeks) {
        this.regions = regions;
        this.epiweeks = epiweeks;
    }

    // getters and setters if needed
}

