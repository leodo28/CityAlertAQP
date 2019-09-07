package com.example.tecsup.city_alert_aqp_ale.Clases;

import java.util.Date;

public class Cls_Usuario {
    private Integer id;
    private String usuario;
    private String password;
    private boolean estado;
    private Date ultimafechamodif;
    private Date ultimologin;
    private Cls_Persona personaId;
    private Cls_Rol rolId;

    public Cls_Usuario() {
    }

    public Cls_Usuario(Integer id) {
        this.id = id;
    }

    public Cls_Usuario(Integer id, String usuario, String password, boolean estado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getUltimologin() {
        return ultimologin;
    }

    public void setUltimologin(Date ultimologin) {
        this.ultimologin = ultimologin;
    }

    public Cls_Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Cls_Persona personaId) {
        this.personaId = personaId;
    }

    public Cls_Rol getRolId() {
        return rolId;
    }

    public void setRolId(Cls_Rol rolId) {
        this.rolId = rolId;
    }

}
