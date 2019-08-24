package com.example.tecsup.city_alert_aqp_ale;



import com.example.tecsup.city_alert_aqp_ale.Clases.Cls_Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiciosPersona {
    @GET("personas")
    Call<List<Cls_Persona>> listarSerenos();

    @GET("persona/{id}")
    Call<Cls_Persona> obtenerSupervisor(@Path("id") Integer id);

    @GET("persona/{id}")
    Call<Cls_Persona> obtenerUsuario(@Path("id") Integer id);
}
