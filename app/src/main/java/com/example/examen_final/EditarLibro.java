package com.example.examen_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.examen_final.entities.Libro;
import com.example.examen_final.factories.RetrofitFactory;
import com.example.examen_final.services.LibroService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditarLibro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);

        String animeJson = getIntent().getStringExtra("Libro");
        Libro libro = new Gson().fromJson(animeJson, Libro.class);

        EditText etTituloLibroE = findViewById(R.id.etTituloLibroE);
        EditText etResumenLibroE = findViewById(R.id.etResumenLibroE);
        EditText etTiendaLibroE = findViewById(R.id.etTiendaLibroE);

        Button btnAct = findViewById(R.id.btnActualizarDatos);
        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.build();
                Libro libro2 = new Libro();
                LibroService service = retrofit.create(LibroService.class);

                libro2.Caratula = libro.Caratula;
                libro2.Titulo = String.valueOf(etTituloLibroE);
                libro2.Resumen = String.valueOf(etResumenLibroE);
                libro2.Tienda = String.valueOf(etTiendaLibroE);
                Call<Libro> call = service.update(libro.id,libro2);
                call.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        Log.i("Actualizar_EF", new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {
                        Log.i("Actualizar_EF","No se pudo actualizar");
                    }
                });
            }
        });
    }
}