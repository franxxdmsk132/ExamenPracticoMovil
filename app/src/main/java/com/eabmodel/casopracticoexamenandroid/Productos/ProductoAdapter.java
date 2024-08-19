package com.eabmodel.casopracticoexamenandroid.Productos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eabmodel.casopracticoexamenandroid.R;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.tvNombreProducto.setText(producto.getNombre());
        holder.tvNombreCategoria.setText(producto.getCategoria().getNombre_categoria());
        holder.tvDescripcionProducto.setText(producto.getDescripcion());
        holder.tvStockProducto.setText(String.valueOf(producto.getStock()));
        holder.tvUbicacionProducto.setText(producto.getUbicacion());
        holder.tvFechaCaducidadProducto.setText(producto.getFecha_caducidad());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreProducto;
        TextView tvNombreCategoria;
        TextView tvDescripcionProducto;
        TextView tvStockProducto;
        TextView tvUbicacionProducto;
        TextView tvFechaCaducidadProducto;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvNombreCategoria = itemView.findViewById(R.id.tvNombreCategoria);
            tvDescripcionProducto = itemView.findViewById(R.id.tvDescripcionProducto);
            tvStockProducto = itemView.findViewById(R.id.tvStockProducto);
            tvUbicacionProducto = itemView.findViewById(R.id.tvUbicacionProducto);
            tvFechaCaducidadProducto = itemView.findViewById(R.id.tvFechaCaducidadProducto);
        }
    }
}
