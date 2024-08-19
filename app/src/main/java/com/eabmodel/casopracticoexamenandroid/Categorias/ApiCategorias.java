package com.eabmodel.casopracticoexamenandroid.Categorias;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCategorias {
    @GET("/categorias/listar")
    Call<List<Categoria>> listarCategorias();

    @GET("/categorias/listar/{id}")
    Call<Categoria> obtenerCategoriaPorId(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("/categorias/guardar")
    Call<Mensaje> crearCategoria(@Field("nombre_categoria") String nombreCategoria);

    @FormUrlEncoded
    @PUT("/categorias/actualizar/{id}")
    Call<Mensaje> actualizarCategoria(@Path("id") int id, @Field("nombre_categoria") String nombreCategoria);

    @DELETE("/categorias/eliminar/{id}")
    Call<Mensaje> eliminarCategoria(@Path("id") Integer id);
}
