package com.example.AgendaContactosEjSpring2.Controlador;

import com.example.AgendaContactosEjSpring2.Dto.UsuarioRegistroDto;
import com.example.AgendaContactosEjSpring2.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @ModelAttribute("usuario")
    public UsuarioRegistroDto retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDto();
    }

    @GetMapping
    public String MostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDto registroDTO){
        usuarioServicio.guardar(registroDTO);
        return "redirect:/registro?exito";
    }

}
