package com.example.tecsup.city_alert_aqp_ale.Clases;

import java.util.Date;

public class Cls_Incidencia {
    int id;
    boolean estado;
    String fechacreacion;
    Date fecha;
    Cls_Tipo_Incidencia tipoIncidenciaId;
    Cls_Persona personaId;
    Cls_Estado_Incidencia estadoIncidenciaId;
    Cls_Persona serenoId;

    public Cls_Incidencia() {
    }

    public Cls_Incidencia(Date fecha, Cls_Tipo_Incidencia tipo_incidencia, Cls_Persona sereno) {
        this.fecha = fecha;
        this.tipoIncidenciaId = tipo_incidencia;
        this.serenoId = sereno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cls_Tipo_Incidencia getTipo_incidencia() {
        return tipoIncidenciaId;
    }

    public void setTipo_incidencia(Cls_Tipo_Incidencia tipo_incidencia) {
        this.tipoIncidenciaId = tipo_incidencia;
    }

    public Cls_Persona getSereno() {
        return serenoId;
    }

    public void setSereno(Cls_Persona sereno) {
        this.serenoId = sereno;
    }

    public Cls_Estado_Incidencia getEstado_incidencia_id() {
        return estadoIncidenciaId;
    }

    public void setEstado_incidencia_id(Cls_Estado_Incidencia estado_incidencia_id) {
        this.estadoIncidenciaId = estado_incidencia_id;
    }

    public Cls_Persona getPersona() {
        return personaId;
    }

    public void setPersona(Cls_Persona ciudadano) {
        this.personaId = ciudadano;
    }
}
