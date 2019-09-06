package com.example.tecsup.city_alert_aqp_ale;

import com.example.tecsup.city_alert_aqp_ale.Clases.Cls_Persona;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiciosLogin {
    @POST("/usuario/login/")
    @FormUrlEncoded
    Call<Cls_Persona> VerificarLogin(@Field("Dni")String dni, @Field("contraseña")String pass);
    }

