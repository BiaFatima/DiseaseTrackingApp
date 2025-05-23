package com.example.DiseaseTrackingApp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPIInterface {
    String BASE_URL = "https://disease.sh/v3/covid-19/";

    @GET("countries")
    Call<List<CovidModelClass>> getCountryData();

}
