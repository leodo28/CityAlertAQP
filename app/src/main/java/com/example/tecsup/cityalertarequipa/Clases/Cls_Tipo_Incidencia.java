package com.example.tecsup.cityalertarequipa.Clases;

import java.io.Serializable;

public class Cls_Tipo_Incidencia implements Serializable {
    int id;
    String descripcion;
    boolean estado;

    public Cls_Tipo_Incidencia() {
    }

    public Cls_Tipo_Incidencia(String descripcion, boolean estado) {
        this.descripcion = descripcion;
        this.estado = estado;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
