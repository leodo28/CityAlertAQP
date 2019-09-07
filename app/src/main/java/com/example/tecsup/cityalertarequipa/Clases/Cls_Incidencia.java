package com.example.tecsup.cityalertarequipa.Clases;

import java.io.Serializable;
import java.util.Date;

public class Cls_Incidencia implements Serializable {
    int id;
    boolean estado;
    long FechaCreacionInt;
    Cls_Tipo_Incidencia tipoIncidenciaId;
    Cls_Persona personaId;
    Cls_Estado_Incidencia estadoIncidenciaId;
    Cls_Persona serenoId;


    public Cls_Incidencia() {

    }


    public Cls_Incidencia(Cls_Tipo_Incidencia tipo_incidencia, Cls_Persona sereno) {
        this.tipoIncidenciaId = tipo_incidencia;
        this.serenoId = sereno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getFecha_creacion() {
        return FechaCreacionInt;
    }

    public void setFecha_creacion(long fecha_creacion) {
        this.FechaCreacionInt = fecha_creacion;
    }

    public Cls_Tipo_Incidencia getTipoIncidenciaId() {
        return tipoIncidenciaId;
    }

    public void setTipoIncidenciaId(Cls_Tipo_Incidencia tipoIncidenciaId) {
        this.tipoIncidenciaId = tipoIncidenciaId;
    }

    public Cls_Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Cls_Persona personaId) {
        this.personaId = personaId;
    }

    public Cls_Estado_Incidencia getEstadoIncidenciaId() {
        return estadoIncidenciaId;
    }

    public void setEstadoIncidenciaId(Cls_Estado_Incidencia estadoIncidenciaId) {
        this.estadoIncidenciaId = estadoIncidenciaId;
    }

    public Cls_Persona getSerenoId() {
        return serenoId;
    }

    public void setSerenoId(Cls_Persona serenoId) {
        this.serenoId = serenoId;
    }
}
