package com.example.AgendaContactosEjSpring2.servicio;

import java.util.List;

import com.example.AgendaContactosEjSpring2.Dto.UsuarioRegistroDto;
import com.example.AgendaContactosEjSpring2.Entidad.Contacto;
import com.example.AgendaContactosEjSpring2.Entidad.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServicio extends UserDetailsService{

    public Usuario guardar(UsuarioRegistroDto registroDTO);

    public List<Usuario> listarUsuarios();

}