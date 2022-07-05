package com.example.examen_final.factories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static Retrofit build(){
        return new Retrofit.Builder()
                .baseUrl("https://62c40e2c7d83a75e39eea411.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
