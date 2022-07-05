package com.example.examen_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.examen_final.adapter.LibrosAdapter;
import com.example.examen_final.entities.Libro;
import com.example.examen_final.factories.RetrofitFactory;
import com.example.examen_final.services.LibroService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListarLibros extends AppCompatActivity {
    List<Libro> libros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libros);

        Retrofit retrofit = RetrofitFactory.build();

        LibroService service = retrofit.create(LibroService.class);
        Call<List<Libro>> call = service.getLibros();

        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if (response.isSuccessful()) {
                    libros = response.body();

                    LibrosAdapter adapter = new LibrosAdapter(libros);

                    RecyclerView rv = findViewById(R.id.rvLibros);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {

            }
        });
        FloatingActionButton fabButton = findViewById(R.id.fabCreate);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(intent);
            }
        });
    }
}