package com.example.tecsup.cityalertarequipa.Clases;

public class Cls_Incidencia {
    String hora;
    String fecha;
    String tipo;
    Cls_Persona sereno;
    String estado;

    public Cls_Incidencia(String fecha,String hora, String tipo, Cls_Persona sereno, String estado) {
        this.hora = hora;
        this.tipo = tipo;
        this.sereno = sereno;
        this.estado = estado;
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cls_Persona getSereno() {
        return sereno;
    }

    public void setSereno(Cls_Persona sereno) {
        this.sereno = sereno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
