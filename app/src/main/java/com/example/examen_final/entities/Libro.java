package com.example.examen_final.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libro")
public class Libro {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Titulo")
    public String Titulo;
    @ColumnInfo(name = "Resumen")
    public String Resumen;
    @ColumnInfo(name = "Tienda")
    public String Tienda;
    @ColumnInfo(name = "Caratula")
    public String Caratula;

    public Libro() {
    }

    public Libro(int id, String caratula, String titulo, String resumen, String tienda) {
        this.id = id;
        Caratula = caratula;
        Titulo = titulo;
        Resumen = resumen;
        Tienda = tienda;
    }
}
