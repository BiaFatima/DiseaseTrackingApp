package com.example.DiseaseTrackingApp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FluResponse {
    @SerializedName("result")
    private int result;

    @SerializedName("message")
    private String message;

    @SerializedName("epidata")
    private List<InfluenzaModelClass> epidata;

    // getters and setters
    public int getResult() { return result; }
    public String getMessage() { return message; }
    public List<InfluenzaModelClass> getEpidata() { return epidata; }
}


