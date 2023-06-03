package com.example.AgendaContactosEjSpring2.Repositorio;

import com.example.AgendaContactosEjSpring2.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    public Usuario findByEmail(String email);

}
