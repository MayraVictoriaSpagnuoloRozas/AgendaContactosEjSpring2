package com.example.AgendaContactosEjSpring2.Controlador;

import com.example.AgendaContactosEjSpring2.Dto.UsuarioRegistroDto;
import com.example.AgendaContactosEjSpring2.Entidad.Contacto;
import com.example.AgendaContactosEjSpring2.servicio.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoControlador {
    @Autowired//inyecta la dependencia necesaria
    private ContactoServicio contactoServicio;
    //@RequestMapping estaria llamando a los metodos del controlador luego de tomar una peticin de la vista
    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo) {//ModelMap pasa variables del controlador a nuestro html
        List<Contacto> contacto = contactoServicio.listarTodosLosContactos();

        modelo.addAttribute("contactos", contacto);//addAtribute manda 2 argumentos, el identificador
        //que coincide con el Thymeleaf y el objeto que queremos mandar por html
        return "index";// en el index me va a aparecer asi: th:text=”${nombre}”
    }

    @GetMapping({"/contactos", "/"})
    public String listarContactos (Model modelo){
        modelo.addAttribute("contactos", new Contacto());
        return "nuevo";
    }

    @PostMapping("/save")//asigna un metodo a la peticion de la vista
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult,
                                  RedirectAttributes redirect, Model modelo) {

        if(bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "nuevo";
        }

        contactoServicio.guardarContacto(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha" +
                " sido agregado con exito");

        return "redirect:/";
    }


    @GetMapping("/editar/{id}")//Path es ruta: / lo que viene despuès de la barra
    public String mostrarFormularioDeEditarContacto(@PathVariable Long id,Model modelo) {
        Contacto contacto = contactoServicio.obtenerContactoPorId(id);
        modelo.addAttribute("contacto", contacto);
        return "editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarContacto(@PathVariable Long id,@Validated Contacto contacto,
                                     BindingResult bindingResult,RedirectAttributes redirect,Model modelo) {

        Contacto contactoDB = contactoServicio.obtenerContactoPorId(id);
        if(bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "editar";
        }

        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setTelefono(contacto.getTelefono());


        contactoServicio.guardarContacto(contactoDB);
        redirect.addFlashAttribute("msgExito", "El contacto " +
                "ha sido actualizado correctamente");

        return "redirect:/";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id,RedirectAttributes redirect) {
        Contacto contacto = contactoServicio.obtenerContactoPorId(id);

        contactoServicio.eliminarContacto((long) contacto.getId());

        redirect.addFlashAttribute("msgExito", "El contacto ha " +
                "sido eliminado correctamente");

        return "redirect:/";
    }

    @ModelAttribute("contacto")
    public UsuarioRegistroDto retornarNuevoUsuarioRegistroDto() {

        return new UsuarioRegistroDto();
    }
    }

