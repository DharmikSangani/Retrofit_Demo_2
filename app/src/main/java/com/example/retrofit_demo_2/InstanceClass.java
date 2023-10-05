package com.example.retrofit_demo_2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceClass {
    public static CallApi_Interface API_Calling()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dharmikandroid.000webhostapp.com/MyApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CallApi_Interface service = retrofit.create(CallApi_Interface.class);
        return service;
    }
}
