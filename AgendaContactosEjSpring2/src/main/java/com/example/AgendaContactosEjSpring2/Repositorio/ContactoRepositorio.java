package com.example.AgendaContactosEjSpring2.Repositorio;

import com.example.AgendaContactosEjSpring2.Entidad.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepositorio extends JpaRepository<Contacto, Long> {
}
