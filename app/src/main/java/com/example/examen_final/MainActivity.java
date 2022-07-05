package com.example.examen_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.examen_final.database.AppDatabase;
import com.example.examen_final.entities.Libro;
import com.example.examen_final.factories.RetrofitFactory;
import com.example.examen_final.services.LibroService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnListar = findViewById(R.id.btnListar);
        Button btnFavoritos = findViewById(R.id.btnFavoritos);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListarLibros.class);
                startActivity(intent);
            }
        });
    }
}