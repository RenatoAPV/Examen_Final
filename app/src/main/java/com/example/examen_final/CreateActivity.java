package com.example.examen_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.examen_final.entities.Image;
import com.example.examen_final.entities.ImagePost;
import com.example.examen_final.entities.Libro;
import com.example.examen_final.factories.RetrofitFactory;
import com.example.examen_final.factories.RetrofitImageFactory;
import com.example.examen_final.services.ImageService;
import com.example.examen_final.services.LibroService;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_PICK_IMAGE = 1001;
    private static final int PHOTO_SELECTED = 101;
    static final int REQUEST_CAMERA_PERMISSION = 100;

    String imagen;
    ImageView fotoLibro;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Button btnTomarFoto = findViewById(R.id.btnTomarFoto);
        Button btnGalleria = findViewById(R.id.btnGaleria);
        fotoLibro = findViewById(R.id.ivLibroRegistro);
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
            }
        });
        btnGalleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        EditText etTitulo = findViewById(R.id.etTituloC);
        EditText etResumen = findViewById(R.id.etResumenC);
        EditText etTienda = findViewById(R.id.etTiendaC);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.build();

                LibroService service = retrofit.create(LibroService.class);
                Libro libro = new Libro();

                libro.Titulo = String.valueOf(etTitulo.getText());
                libro.Resumen = String.valueOf(etResumen.getText());
                libro.Tienda = String.valueOf(etTienda.getText());

                /*ImagePost imagePost = new ImagePost();
                imagePost.image = imagen;
                Retrofit retrofitImage = RetrofitImageFactory.build(getApplicationContext());
                ImageService imageService = retrofitImage.create(ImageService.class);

                Call<Image> callImage = imageService.create(imagePost);
                callImage.enqueue(new Callback<Image>() {
                    @Override
                    public void onResponse(Call<Image> call, Response<Image> response) {
                        libro.Caratula = response.body().data.link;
                        Call<Libro> callLibro = service.create(libro);

                        callLibro.enqueue(new Callback<Libro>() {
                            @Override
                            public void onResponse(Call<Libro> call, Response<Libro> response) {
                                if(response.isSuccessful()){
                                    Log.i("registro", new Gson().toJson(response.body()));
                                }
                            }

                            @Override
                            public void onFailure(Call<Libro> call, Throwable t) {
                                Log.i("registro", "Error registro");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Image> call, Throwable t) {

                    }
                });*/

                libro.Caratula = imagen;
                Call<Libro> callLibro = service.create(libro);
                callLibro.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        if(response.isSuccessful()){
                            Log.i("registro", new Gson().toJson(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {
                        Log.i("registro", "Error registro");
                    }
                });


            }
        });
    }
    public static String convertS(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {

        }
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen = convertS(imageBitmap);
            fotoLibro.setImageBitmap(imageBitmap);

        }
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap imageBitmap = BitmapFactory.decodeStream(bufferedInputStream);
                imagen = convertS(imageBitmap);
                fotoLibro.setImageBitmap(imageBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        /*if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            fotoLibro.setImageURI(imageUri);
            Bitmap bmp = BitmapFactory.decodeStream(imageUri);
            imagen = convertS(bmp);
        }*/
    }
}