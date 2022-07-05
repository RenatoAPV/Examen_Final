package com.example.examen_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examen_final.entities.Libro;
import com.google.gson.Gson;

public class LibroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_actitivity);

        String libroJSON = getIntent().getStringExtra("Libro");
        Libro libro = new Gson().fromJson(libroJSON, Libro.class);

        ImageView ivCaratulaD = findViewById(R.id.ivCaratulaDetalle);
        TextView tvTituloD = findViewById(R.id.tvTituloDetalle);
        TextView tvResumenD = findViewById(R.id.tvResumenDetalle);

        ivCaratulaD.setImageBitmap(convertB(libro.Caratula));
        tvTituloD.setText(libro.Titulo);
        tvResumenD.setText(libro.Resumen);

        Button btnEditar = findViewById(R.id.btnEditar);
        Button btnMapa = findViewById(R.id.btnMapa);
        Button btnFav = findViewById(R.id.btnFav);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditarLibro.class);

                String libroJson = new Gson().toJson(libro);
                intent.putExtra("Libro", libroJson);

                startActivity(intent);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                intent.putExtra("Libro", libro.Tienda);
                startActivity(intent);
            }
        });
    }


    public static Bitmap convertB(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}