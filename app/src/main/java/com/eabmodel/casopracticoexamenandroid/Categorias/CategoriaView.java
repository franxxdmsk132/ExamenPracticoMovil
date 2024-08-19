package com.eabmodel.casopracticoexamenandroid.Categorias;

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

import com.eabmodel.casopracticoexamenandroid.R;
import com.eabmodel.casopracticoexamenandroid.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaView extends AppCompatActivity {

    private RecyclerView recyclerViewCategoria;
    private CategoriaAdapter categoriaAdapter;
    private List<Categoria> categoriaList = new ArrayList<>();
    private List<Categoria> categoriaListFiltered = new ArrayList<>();
    private Button buttonAgregarCategoria;
    private SearchView searchViewCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categoria_view);

        // Inicializa el RecyclerView
        recyclerViewCategoria = findViewById(R.id.recyclerViewCategoria);
        recyclerViewCategoria.setLayoutManager(new LinearLayoutManager(this));

        // Configura el adaptador
        categoriaAdapter = new CategoriaAdapter(categoriaListFiltered);
        recyclerViewCategoria.setAdapter(categoriaAdapter);

        // Inicializa el SearchView
        searchViewCategoria = findViewById(R.id.searchViewCategoria);
        searchViewCategoria.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCategorias(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCategorias(newText);
                return false;
            }
        });

        // Cargar datos
        cargarCategorias();

        // Inicializa el botón
        buttonAgregarCategoria = findViewById(R.id.buttonAgregarCategoria);
        buttonAgregarCategoria.setOnClickListener(v -> {
            // Redirige a la actividad para crear una nueva categoría
            Intent intent = new Intent(CategoriaView.this, CategoriaCrear.class);
            startActivity(intent);
        });
    }

    private void cargarCategorias() {
        ApiCategorias apiCategorias = RetrofitClientInstance.getRetrofitInstance().create(ApiCategorias.class);
        Call<List<Categoria>> call = apiCategorias.listarCategorias();

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoriaList.clear();
                    categoriaList.addAll(response.body());
                    categoriaListFiltered.clear();
                    categoriaListFiltered.addAll(categoriaList);
                    categoriaAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CategoriaView.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(CategoriaView.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterCategorias(String query) {
        String queryLowerCase = query.toLowerCase();
        categoriaListFiltered.clear();
        if (query.isEmpty()) {
            categoriaListFiltered.addAll(categoriaList);
        } else {
            categoriaListFiltered.addAll(
                    categoriaList.stream()
                            .filter(categoria -> categoria.getNombre_categoria().toLowerCase().contains(queryLowerCase))
                            .collect(Collectors.toList())
            );
        }
        categoriaAdapter.notifyDataSetChanged();
    }
}