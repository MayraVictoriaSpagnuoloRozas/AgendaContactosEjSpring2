package com.example.AgendaContactosEjSpring2.Entidad;
import javax.persistence.*;

//Igual que en JPA- Declara una clase como entidad, que sera una tabla con sus respectivas columnas
@Entity
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "telefono", length = 100, nullable = false)
    private String telefono;

    public Contacto(){

    }
    public Contacto(long id, String nombre, String telefono) {
        id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
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
}