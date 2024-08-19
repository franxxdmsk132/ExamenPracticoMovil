package com.eabmodel.casopracticoexamenandroid.Productos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eabmodel.casopracticoexamenandroid.Categorias.ApiCategorias;
import com.eabmodel.casopracticoexamenandroid.Categorias.Categoria;
import com.eabmodel.casopracticoexamenandroid.Categorias.Mensaje;
import com.eabmodel.casopracticoexamenandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoCrear extends AppCompatActivity {

//    private EditText etIdProducto;
    private EditText etNombreProducto;
    private EditText etDescripcionProducto;
    private Spinner spinnerCategoriaProducto;
    private EditText etStockProducto;
    private EditText etUbicacionProducto;
    private EditText etFechaCaducidadProducto;
    private Button btnGuardarProducto;

    private ApiProductos apiProductos;
    private ApiCategorias apiCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_crear);

        // Vincular los elementos de la interfaz con el código
//        etIdProducto = findViewById(R.id.etIdProducto);
        etNombreProducto = findViewById(R.id.etNombreProducto);
        etDescripcionProducto = findViewById(R.id.etDescripcionProducto);
        spinnerCategoriaProducto = findViewById(R.id.spinnerCategoriaProducto);
        etStockProducto = findViewById(R.id.etStockProducto);
        etUbicacionProducto = findViewById(R.id.etUbicacionProducto);
        etFechaCaducidadProducto = findViewById(R.id.etFechaCaducidadProducto);
        btnGuardarProducto = findViewById(R.id.btnGuardarProducto);

        // Inicializar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.0.1.159:8080") // Cambia esto por tu URL base
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiProductos = retrofit.create(ApiProductos.class);
        apiCategorias = retrofit.create(ApiCategorias.class);

        // Configurar el botón para guardar el producto
        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarProducto();
            }
        });

        // Cargar las categorías en el Spinner
        cargarCategorias();
    }

    private void cargarCategorias() {
        Call<List<Categoria>> call = apiCategorias.listarCategorias();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Categoria> listaCategorias = response.body();
                    ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(ProductoCrear.this,
                            android.R.layout.simple_spinner_item, listaCategorias);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategoriaProducto.setAdapter(adapter);
                } else {
                    Toast.makeText(ProductoCrear.this, "Error al cargar categorías", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(ProductoCrear.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarProducto() {
        // Obtener los valores ingresados por el usuario
//        int id = Integer.parseInt(etIdProducto.getText().toString());
        String nombre = etNombreProducto.getText().toString();
        String descripcion = etDescripcionProducto.getText().toString();
        Categoria categoria = (Categoria) spinnerCategoriaProducto.getSelectedItem();
        int stock = Integer.parseInt(etStockProducto.getText().toString());
        String ubicacion = etUbicacionProducto.getText().toString();
        String fechaCaducidad = etFechaCaducidadProducto.getText().toString();

        // Validaciones básicas
        if (nombre.isEmpty() || descripcion.isEmpty() || ubicacion.isEmpty() || fechaCaducidad.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Llamar al API para guardar el producto
        Call<Mensaje> call = apiProductos.crearProducto(nombre, descripcion, categoria.getId(), stock, ubicacion, fechaCaducidad);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProductoCrear.this, "Producto guardado exitosamente", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                    finish();
                } else {
                    Toast.makeText(ProductoCrear.this, "Error al guardar producto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                Toast.makeText(ProductoCrear.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarCampos() {
//        etIdProducto.setText("");
        etNombreProducto.setText("");
        etDescripcionProducto.setText("");
        spinnerCategoriaProducto.setSelection(0);
        etStockProducto.setText("");
        etUbicacionProducto.setText("");
        etFechaCaducidadProducto.setText("");
    }
}