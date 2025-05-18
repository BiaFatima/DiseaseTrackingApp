package com.example.DiseaseTrackingApp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfluenzaApiUtilities {

    private static Retrofit retrofit = null;

    public static InfluenzaAPIInterface getAPIInterface() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.100.56:5000/") // <-- This is your base URL
                    .addConverterFactory(GsonConverterFactory.create()) // For JSON parsing
                    .build();
        }
        return retrofit.create(InfluenzaAPIInterface.class);
    }

}
