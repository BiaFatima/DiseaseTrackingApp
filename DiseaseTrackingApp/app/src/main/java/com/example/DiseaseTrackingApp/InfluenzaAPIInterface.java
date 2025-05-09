package com.example.DiseaseTrackingApp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InfluenzaAPIInterface {
    String BASE_URL = "https://data.cdc.gov/resource/";

    @GET("2ew6-ywp6.json")
    Call<List<InfluenzaModelClass>> getUSInfluenzaData();
}
