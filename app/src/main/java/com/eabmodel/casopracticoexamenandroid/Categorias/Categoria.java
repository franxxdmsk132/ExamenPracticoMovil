package com.eabmodel.casopracticoexamenandroid.Categorias;

public class Categoria {
    private int id;
    private String nombre_categoria;

    // Constructor sin parámetros
    public Categoria() {}

    // Constructor con parámetros
    public Categoria(int id, String nombre_categoria) {
        this.id = id;
        this.nombre_categoria = nombre_categoria;
    }
    @Override
    public String toString() {
        return nombre_categoria; // Devuelve el nombre de la categoría
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
