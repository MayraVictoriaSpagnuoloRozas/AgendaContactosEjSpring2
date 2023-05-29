package com.example.AgendaContactosEjSpring2.Entidad;
import javax.persistence.*;


@Entity
@Table(name = "rol")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Roles(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Roles() {
    }

    public Roles(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
