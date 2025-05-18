package com.example.DiseaseTrackingApp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InfluenzaAPIInterface {
    @GET("api/influenza")
    Call<PagedResponse<InfluenzaModelClass>> getFluData(
            @Query("page") int page,
            @Query("pageSize") int pageSize,
            @Query("country") String country);
}
