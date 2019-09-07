package com.example.tecsup.city_alert_aqp_ale.Clases;

import java.util.Date;

class Cls_Rol {
    private Integer id;
    private String descripcion;
    private boolean estado;
    private Date ultimafechamodif;

    public Cls_Rol() {
    }

    public Cls_Rol(Integer id) {
        this.id = id;
    }

    public Cls_Rol(Integer id, String descripcion, boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getUltimafechamodif() {
        return ultimafechamodif;
    }

    public void setUltimafechamodif(Date ultimafechamodif) {
        this.ultimafechamodif = ultimafechamodif;
    }

}
