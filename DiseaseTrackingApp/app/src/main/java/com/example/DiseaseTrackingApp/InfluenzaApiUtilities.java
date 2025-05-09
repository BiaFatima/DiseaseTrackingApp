package com.example.DiseaseTrackingApp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfluenzaApiUtilities {

    private static Retrofit retrofit = null;

    public static InfluenzaAPIInterface getAPIInterface() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://data.cdc.gov/resource/")  // Correct base URL for CDC API
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(InfluenzaAPIInterface.class);
    }
}
