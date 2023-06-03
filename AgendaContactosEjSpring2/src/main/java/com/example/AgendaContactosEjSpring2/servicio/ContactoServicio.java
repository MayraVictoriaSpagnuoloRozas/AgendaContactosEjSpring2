package com.example.AgendaContactosEjSpring2.servicio;

import com.example.AgendaContactosEjSpring2.Dto.UsuarioRegistroDto;
import com.example.AgendaContactosEjSpring2.Entidad.Contacto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ContactoServicio extends UserDetailsService {
    public List<Contacto> listarTodosLosContactos();

    public Contacto guardarContacto(Contacto contacto);

    public Contacto obtenerContactoPorId(Long id);

    public Contacto actualizarContacto(Contacto contacto);

    public void eliminarContacto(Long id);


    // public guardarRol (ContactoRegistroDto); que este metodo en la implementacion gurde en array:
    //Arrays.asList(new Rol("ROLE_USER")) y que tenga un menú para elegir rol? y que tenga diferentes autenticaciones
    // de acceso según el mismo?

    public UsuarioRegistroDto retornarNuevoUsuarioRegistroDTO();
}
