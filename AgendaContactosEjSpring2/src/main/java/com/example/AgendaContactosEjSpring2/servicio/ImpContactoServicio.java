package com.example.AgendaContactosEjSpring2.servicio;

import com.example.AgendaContactosEjSpring2.Dto.UsuarioRegistroDto;
import com.example.AgendaContactosEjSpring2.Entidad.Contacto;
import com.example.AgendaContactosEjSpring2.Repositorio.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

 @Service
 public class ImpContactoServicio implements ContactoServicio, UserDetailsService {


     @Autowired
     private ContactoRepositorio repositorio;


     @Override
     public List<Contacto> listarTodosLosContactos() {

         return repositorio.findAll();
     }

     @Override
     public Contacto guardarContacto(Contacto contacto) {

         return repositorio.save(contacto);
     }

     @Override
     public Contacto obtenerContactoPorId(Long id) {

         return repositorio.findById(id).get();
     }

     @Override
     public Contacto actualizarContacto(Contacto contacto) {
         return repositorio.save(contacto);
     }

     @Override
     public void eliminarContacto(Long id) {
         repositorio.deleteById(id);

     }

     @Override
     public UsuarioRegistroDto retornarNuevoUsuarioRegistroDTO() {
         return null;
     }

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         return null;
     }
 }
