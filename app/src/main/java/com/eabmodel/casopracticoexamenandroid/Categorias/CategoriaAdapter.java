package com.eabmodel.casopracticoexamenandroid.Categorias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.eabmodel.casopracticoexamenandroid.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> implements Filterable {

    private final List<Categoria> categoriaList;
    private List<Categoria> categoriaListFull; // Lista completa para el filtrado

    public CategoriaAdapter(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
        this.categoriaListFull = new ArrayList<>(categoriaList); // Crea una copia de la lista completa
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = categoriaList.get(position);
        holder.bind(categoria);
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Categoria> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(categoriaListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Categoria item : categoriaListFull) {
                        // Filtra por nombre_categoria
                        if (item.getNombre_categoria().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Actualiza la lista filtrada y notifica los cambios
                categoriaList.clear();
                categoriaList.addAll((List<Categoria>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombreCategoriaTextView;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreCategoriaTextView = itemView.findViewById(R.id.tvNombreCategoria);
        }

        public void bind(Categoria categoria) {
            nombreCategoriaTextView.setText(categoria.getNombre_categoria());
        }
    }
}