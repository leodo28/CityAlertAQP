package com.example.tecsup.cityalertarequipa;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface ServiciosPersona {
    @GET("personas")
    Call<List<Cls_Persona>> listarSerenos();

    @GET("persona/{id}")
    Call<Cls_Persona> obtenerSupervisor(@Path("id") Integer id);
}
