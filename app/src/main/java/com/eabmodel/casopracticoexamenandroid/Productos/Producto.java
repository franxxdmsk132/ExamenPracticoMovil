package com.eabmodel.casopracticoexamenandroid.Productos;

import com.eabmodel.casopracticoexamenandroid.Categorias.Categoria;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private int stock;
    private String ubicacion;
    private String fecha_caducidad;

    // Constructor sin parámetros
    public Producto() {}

    // Constructor con parámetros
    public Producto(int id, String nombre, String descripcion, Categoria categoria, int stock, String ubicacion, String fecha_caducidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.stock = stock;
        this.ubicacion = ubicacion;
        this.fecha_caducidad = fecha_caducidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }
}
