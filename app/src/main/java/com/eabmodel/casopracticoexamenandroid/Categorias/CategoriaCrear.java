package com.eabmodel.casopracticoexamenandroid.Categorias;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eabmodel.casopracticoexamenandroid.R;
import com.eabmodel.casopracticoexamenandroid.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaCrear extends AppCompatActivity {

    private EditText etNombreCategoria;
    private Button btnGuardarCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categoria_crear);

        // Ajustar el espaciado del contenido para adaptarse a las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_categoria_crear), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa las vistas
        etNombreCategoria = findViewById(R.id.etNombreCategoria);
        btnGuardarCategoria = findViewById(R.id.btnGuardarCategoria);

        // Configura el botón para guardar la categoría
        btnGuardarCategoria.setOnClickListener(v -> guardarCategoria());
    }

    private void guardarCategoria() {
        String nombreCategoria = etNombreCategoria.getText().toString().trim();

        if (nombreCategoria.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un nombre para la categoría", Toast.LENGTH_SHORT).show();
            return;
        }

        // Llama al API para guardar la categoría
        ApiCategorias apiCategorias = RetrofitClientInstance.getRetrofitInstance().create(ApiCategorias.class);
        Call<Mensaje> call = apiCategorias.crearCategoria(nombreCategoria);

        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(CategoriaCrear.this, "Categoría guardada con éxito", Toast.LENGTH_SHORT).show();
                    finish(); // Cierra la actividad
                } else {
                    Toast.makeText(CategoriaCrear.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                Toast.makeText(CategoriaCrear.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}