package com.eabmodel.casopracticoexamenandroid.Productos;

import com.eabmodel.casopracticoexamenandroid.Categorias.Mensaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiProductos {

    @GET("/productos/listar")
    Call<List<Producto>> listarProductos();

    @GET("/productos/listar/{id}")
    Call<Producto> obtenerProductoPorId(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("/productos/guardar")
    Call<Mensaje> crearProducto(
            @Field("nombre") String nombre,
            @Field("descripcion") String descripcion,
            @Field("categoriaId") Integer categoriaId,
            @Field("stock") Integer stock,
            @Field("ubicacion") String ubicacion,
            @Field("fecha_caducidad") String fecha);

    @FormUrlEncoded
    @PUT("/productos/actualizar/{id}")
    Call<Mensaje> actualizarProducto(
            @Path("id") Integer id,
            @Field("nombre") String nombre,
            @Field("descripcion") String descripcion,
            @Field("categoriaId") Integer categoriaId,
            @Field("stock") Integer stock,
            @Field("ubicacion") String ubicacion,
            @Field("fecha_caducidad") String fecha);

    @DELETE("/productos/eliminar/{id}")
    Call<Mensaje> eliminarProducto(@Path("id") Integer id);
}