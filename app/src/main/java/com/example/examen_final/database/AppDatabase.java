package com.example.examen_final.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examen_final.dao.LibroDao;
import com.example.examen_final.entities.Libro;

@Database(entities = {Libro.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LibroDao peliculaDao();

    public static AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "database.Libro.db")
                .allowMainThreadQueries()
                .build();
    }
}