package com.example.examen_final.services;

import com.example.examen_final.entities.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LibroService {
    @GET("Libro")
    Call<List<Libro>> getLibros();

    @POST("Libro")
    Call<Libro> create(@Body Libro libro);

    @PUT("Libro/{id}")
    Call<Libro> update(@Path("id") int id, @Body Libro libro);
}
