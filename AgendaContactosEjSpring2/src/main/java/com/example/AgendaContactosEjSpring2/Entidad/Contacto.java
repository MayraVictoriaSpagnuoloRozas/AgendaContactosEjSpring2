package com.example.AgendaContactosEjSpring2.Entidad;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@Table(name="Contacto")

public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private long id;

    @NotBlank(message= "Ingrese su nombre")
    private String nombre;

    @NotBlank(message="Debe ingresar su celular")
    private int celular;

    @NotEmpty(message="Debe ingresar su email")
    @Email
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message= "Ingrese su fecha de nacimiento")
    private LocalDate fechaCumpleaños;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)// momento preciso en que hace la persistencia en la DB
    @Past
    private LocalDateTime fechaRegistro;
    // deberia tener un atributo que sea rol???
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//no es major one to many porque cada contacto va a tener un solo rol
    //estudiante-profe-conduccion-admin
    @JoinTable(
            name = "contacto_roles",
            joinColumns = @JoinColumn(name = "contacto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id")
    )
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

    public LocalDate getFechaCumpleaños() {
        return fechaCumpleaños;
    }

    public void setFechaCumpleaños(LocalDate fechaCumpleaños) {
        this.fechaCumpleaños = fechaCumpleaños;
    }

    @PrePersist
    public void asignarFechaRegistro() {
        fechaRegistro = LocalDateTime.now();
    }
}
