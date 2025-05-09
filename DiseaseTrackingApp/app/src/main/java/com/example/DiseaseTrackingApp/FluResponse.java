package com.example.DiseaseTrackingApp;

import com.example.DiseaseTrackingApp.InfluenzaModelClass;

import java.util.List;

// Wrapper class for the API response
public class FluResponse {

    // This field will hold the list of InfluenzaModelClass objects
    private List<InfluenzaModelClass> data;

    // Getter for the data list
    public List<InfluenzaModelClass> getData() {
        return data;
    }

    // Setter for the data list
    public void setData(List<InfluenzaModelClass> data) {
        this.data = data;
    }
}
