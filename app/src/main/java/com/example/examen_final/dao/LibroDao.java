package com.example.examen_final.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.examen_final.entities.Libro;

import java.util.List;

@Dao
public interface LibroDao {
    @Query("SELECT * FROM libro")
    List<Libro> getAll();

    @Query("SELECT * FROM libro WHERE id = :id")
    Libro find(int id);

    @Insert
    void create(Libro pelicula);

    @Query("DELETE FROM libro")
    void deletelist();
}
