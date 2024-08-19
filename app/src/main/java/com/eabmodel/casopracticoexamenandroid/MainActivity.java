package com.eabmodel.casopracticoexamenandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eabmodel.casopracticoexamenandroid.Categorias.CategoriaView;
import com.eabmodel.casopracticoexamenandroid.Productos.ProductoView;

public class MainActivity extends AppCompatActivity {

    private Button buttonCategoria;
    private Button buttonProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonCategoria = findViewById(R.id.button);
        buttonProductos = findViewById(R.id.button2);

        // Configurar el clic en el botón de categorías
        buttonCategoria.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriaView.class);
            startActivity(intent);
        });

        // Configurar el clic en el botón de productos
        buttonProductos.setOnClickListener(v -> {
             Intent intent = new Intent(MainActivity.this, ProductoView.class);
             startActivity(intent);
        });
    }
}