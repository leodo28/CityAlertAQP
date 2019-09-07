package com.example.tecsup.cityalertarequipa.Clases;

import java.io.Serializable;

public class  Cls_Estado_Incidencia implements Serializable {
    int id;
    String descripcion;

    public Cls_Estado_Incidencia() {
    }

    public Cls_Estado_Incidencia(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
