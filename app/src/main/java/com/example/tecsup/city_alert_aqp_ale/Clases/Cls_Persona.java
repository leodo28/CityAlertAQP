package com.example.tecsup.city_alert_aqp_ale.Clases;

import java.io.Serializable;

public class Cls_Persona implements Serializable {
    int id;
    String dni;
    String nombres;
    String apellidopaterno;
    String apellidomaterno;
    String email;
    String direccion;
    String telefono;
    boolean estado;
    double latitud;
    double longitud;
    Cls_Persona supervisor;

    public Cls_Persona() {
    }

    public Cls_Persona(String dni, String nombres, String apellidopaterno, String apellidomaterno, String email, String direccion, String telefono) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cls_Persona(String dni, String nombres, String apellidopaterno, String apellidomaterno, String email, String direccion, String telefono, double latitud, double longitud) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
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

    public Cls_Persona getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Cls_Persona supervisor) {
        this.supervisor = supervisor;
    }
}
