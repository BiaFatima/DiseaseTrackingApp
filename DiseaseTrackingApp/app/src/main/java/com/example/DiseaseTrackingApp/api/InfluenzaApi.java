package com.example.DiseaseTrackingApp.api;

import com.example.DiseaseTrackingApp.Models.InfluenzaRecord;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InfluenzaApi {
    @GET("api/influenza/records") // your API endpoint path here
    Call<List<InfluenzaRecord>> getRecords();
}