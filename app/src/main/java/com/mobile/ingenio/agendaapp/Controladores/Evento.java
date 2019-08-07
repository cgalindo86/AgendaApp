package com.mobile.ingenio.agendaapp.Controladores;

public class Evento {

    String fecha;
    String horaI;
    String horaF;
    String titulo;
    String direccion;
    String departamento;
    String municipio;
    String registrador;
    String identificacion;
    String nombre;
    String telefono;
    String jefe;
    String delegado;
    String personas;
    int color;


    public Evento(String fecha, String horaI, String horaF, String titulo, String direccion, String departamento,
                  String municipio, String registrador, String identificacion,
                  String nombre, String telefono, String jefe, String delegado,
                  String personas, int color) {
        this.fecha = fecha;
        this.horaI = horaI;
        this.horaF = horaF;
        this.titulo = titulo;
        this.direccion = direccion;
        this.departamento = departamento;
        this.municipio = municipio;
        this.registrador = registrador;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.jefe = jefe;
        this.delegado = delegado;
        this.personas = personas;
        this.color = color;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraI() {
        return horaI;
    }

    public void setHoraI(String horaI) {
        this.horaI = horaI;
    }

    public String getHoraF() {
        return horaF;
    }

    public void setHoraF(String horaF) {
        this.horaF = horaF;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getRegistrador() {
        return registrador;
    }

    public void setRegistrador(String registrador) {
        this.registrador = registrador;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public String getDelegado() {
        return delegado;
    }

    public void setDelegado(String delegado) {
        this.delegado = delegado;
    }

    public String getPersonas() {
        return personas;
    }

    public void setPersonas(String personas) {
        this.personas = personas;
    }
}
