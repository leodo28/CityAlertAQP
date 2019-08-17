package com.example.tecsup.cityalertarequipa;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Incidencia;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiciosIncidencia {
    @GET("incidencias")
    Call<List<Cls_Incidencia>> listarincidencias();
}
