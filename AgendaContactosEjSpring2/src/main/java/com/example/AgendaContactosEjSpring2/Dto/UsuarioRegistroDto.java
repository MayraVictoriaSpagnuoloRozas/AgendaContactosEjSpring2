package com.example.AgendaContactosEjSpring2.Dto;

//reduce cantidad de datos transmitidos, capa de abstracción entre entidades de dominio y capas externas
// se usa para autenticar usuarios/ puedo ponerle solo nombre y password y que tenga acceso según rol?

public class UsuarioRegistroDto {
    private long id;
    private String nombre;//aca le pondria rol
    private int celular;//sacaria esto
    private String email;// sacaria esto
    private String password;

    public UsuarioRegistroDto(long id, String nombre, int celular, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistroDto(String nombre, int celular, String email, String password) {
        this.nombre = nombre;
        this.celular = celular;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistroDto() {

    }

    public long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
