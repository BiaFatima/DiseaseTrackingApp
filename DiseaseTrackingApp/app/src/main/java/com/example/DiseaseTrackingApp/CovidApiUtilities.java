package com.example.DiseaseTrackingApp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidApiUtilities {
    public static Retrofit retrofit=null;
    public static CovidAPIInterface getAPIInterface(){

        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(CovidAPIInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
return retrofit.create(CovidAPIInterface.class);
    }


}
