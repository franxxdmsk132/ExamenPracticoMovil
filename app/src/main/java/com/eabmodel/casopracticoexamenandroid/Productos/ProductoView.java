package com.eabmodel.casopracticoexamenandroid.Productos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eabmodel.casopracticoexamenandroid.Categorias.CategoriaCrear;
import com.eabmodel.casopracticoexamenandroid.Categorias.CategoriaView;
import com.eabmodel.casopracticoexamenandroid.R;
import com.eabmodel.casopracticoexamenandroid.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoView extends AppCompatActivity {

    private RecyclerView recyclerViewProducto;
    private ProductoAdapter productoAdapter;
    private List<Producto> productoList = new ArrayList<>();
    private List<Producto> productoListFiltered = new ArrayList<>();
    private Button buttonAgregarProducto;
    private SearchView searchViewProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto_view);

        // Inicializa el SearchView
        searchViewProducto = findViewById(R.id.searchViewProducto);
        searchViewProducto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProductos(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProductos(newText);
                return false;
            }
        });

        // Inicializa el RecyclerView
        recyclerViewProducto = findViewById(R.id.recyclerViewProducto);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(this));

        // Configura el adaptador
        productoAdapter = new ProductoAdapter(productoListFiltered);
        recyclerViewProducto.setAdapter(productoAdapter);

        // Cargar datos
        cargarProductos();

        // Inicializa el botón
        buttonAgregarProducto = findViewById(R.id.buttonAgregarProducto);
        buttonAgregarProducto.setOnClickListener(v -> {
            // Redirige a la actividad para crear un nuevo producto
            Intent intent = new Intent(ProductoView.this, ProductoCrear.class);
            startActivity(intent);
        });
    }

    private void cargarProductos() {
        ApiProductos apiProductos = RetrofitClientInstance.getRetrofitInstance().create(ApiProductos.class);
        Call<List<Producto>> call = apiProductos.listarProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productoList.clear();
                    productoList.addAll(response.body());
                    productoListFiltered.clear();
                    productoListFiltered.addAll(productoList);
                    productoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ProductoView.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(ProductoView.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterProductos(String query) {
        String queryLowerCase = query.toLowerCase();
        productoListFiltered.clear();
        if (query.isEmpty()) {
            productoListFiltered.addAll(productoList);
        } else {
            productoListFiltered.addAll(
                    productoList.stream()
                            .filter(producto -> producto.getNombre().toLowerCase().contains(queryLowerCase) ||
                                    producto.getDescripcion().toLowerCase().contains(queryLowerCase))
                            .collect(Collectors.toList())
            );
        }
        productoAdapter.notifyDataSetChanged();
    }
}