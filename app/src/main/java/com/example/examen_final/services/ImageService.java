package com.example.examen_final.services;

import com.example.examen_final.entities.Image;
import com.example.examen_final.entities.ImagePost;
import com.example.examen_final.entities.Libro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ImageService {
    @POST("image")
    Call<Image> create(@Body ImagePost body);
}
