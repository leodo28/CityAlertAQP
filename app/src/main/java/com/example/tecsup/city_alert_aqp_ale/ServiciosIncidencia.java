package com.example.tecsup.city_alert_aqp_ale;



import com.example.tecsup.city_alert_aqp_ale.Clases.Cls_Incidencia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiciosIncidencia {
    @GET("incidencias")
    Call<List<Cls_Incidencia>> listarincidencias();
}
