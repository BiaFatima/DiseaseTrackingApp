package com.example.DiseaseTrackingApp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InfluenzaAPIInterface {

    // Base URL for Delphi FluView API
    String BASE_URL = "https://api.delphi.cmu.edu/epidata/";

    // API endpoint to get FluView data
    @GET("fluview")
    Call<FluResponse> getFluData(
            @Query("source") String source,
            @Query("type") String type,
            @Query("region") String region,
            @Query("date") String date);}
